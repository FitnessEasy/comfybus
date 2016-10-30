package com.example.duytungdao.comfybus.remind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duytungdao.comfybus.JSONobjects.JSONJourney;
import com.example.duytungdao.comfybus.JSONobjects.JSONLeg;
import com.example.duytungdao.comfybus.JSONobjects.JSONRoute;
import com.example.duytungdao.comfybus.JSONobjects.JSONStep;
import com.example.duytungdao.comfybus.Manifest;
import com.example.duytungdao.comfybus.R;
import com.example.duytungdao.comfybus.model.Journey;
import com.example.duytungdao.comfybus.model.Route;
import com.example.duytungdao.comfybus.model.Step;
import com.example.duytungdao.comfybus.remind.GetDataFromServer.GetTransitList;
import com.example.duytungdao.comfybus.remind.controls.RouteItem;
import com.example.duytungdao.comfybus.repository.JourneyRepository;
import com.example.duytungdao.comfybus.repository.PlaceRepository;
import com.example.duytungdao.comfybus.repository.RouteRepository;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duy tung dao on 10/29/2016.
 */

public class ListOfRoutesActivity extends AppCompatActivity{
    public JSONJourney MyTravel;
    public LinearLayout listOfRoutes;
    public TextView TravelInfo;
    private Button btn_AddToList;

    private Place original;
    private Place destination;
    public List<RouteItem> routeItemList;
    private GetTransitList getTransitList;

    public ArrayList<RouteItem> ChosenItems;
    public ArrayList<String> destinationParams;

    private GPSTracker tracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_routes);
        GetOriginalAndDestinationPlace();
        InitComponents();

        // get json string and add to layout
        getTransitList = new GetTransitList(this);
        String[] params = new String[]{original.getId(),destination.getId()};
        getTransitList.execute(params);                                         //get string and add to layout

        MyTravel =  getTransitList.myTravel;
    }

    private void GetOriginalAndDestinationPlace() {
        if(CreateReminderActivity.original!=null&&CreateReminderActivity.destination!=null){
            original = CreateReminderActivity.original;
            destination = CreateReminderActivity.destination;
        }
    }
    private void InitComponents(){
        ChosenItems = new ArrayList<>();

        routeItemList = new ArrayList<>();
        TravelInfo = (TextView)findViewById(R.id.TravelInfo);
        listOfRoutes = (LinearLayout) findViewById(R.id.listOfRoutes);
        btn_AddToList =(Button)findViewById(R.id.btn_AddToList);
    }


    public void onBtnClick(View v){
        switch (v.getId()){
            case R.id.btn_AddToList:
                for (RouteItem i:routeItemList
                     ) {
                    if (i.box.isChecked())
                        ChosenItems.add(i);
                }
                destinationParams = new ArrayList<>();
                for (RouteItem r:ChosenItems
                     ) {
                    for (JSONLeg l: r.MyRout.legs
                         ) {
                        for (JSONStep s:l.steps
                             ) {
                            // if it is bus, we get end location
                            if(s.TravelMode == "TRANSIT"){
                                String i = Double.toString(s.EndLocation[0]) +","+Double.toString(s.EndLocation[0]) ;
                                destinationParams.add(i);
                            }
                        }
                    }

                }

                tracker = new GPSTracker(this,this);
                startService(new Intent(this, GPSTracker.class));
                ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1001);
                if(tracker.location!=null){
                    Toast.makeText(this, tracker.location.toString(), Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
