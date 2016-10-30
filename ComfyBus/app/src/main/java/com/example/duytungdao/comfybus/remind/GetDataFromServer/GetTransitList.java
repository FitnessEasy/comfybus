package com.example.duytungdao.comfybus.remind.GetDataFromServer;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.duytungdao.comfybus.JSONobjects.JSONJourney;
import com.example.duytungdao.comfybus.JSONobjects.JSONLeg;
import com.example.duytungdao.comfybus.JSONobjects.JSONRoute;
import com.example.duytungdao.comfybus.JSONobjects.JSONStep;
import com.example.duytungdao.comfybus.remind.ListOfRoutesActivity;
import com.example.duytungdao.comfybus.remind.controls.RouteItem;
import com.google.android.gms.location.places.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.JarOutputStream;

/**
 * Created by duy tung dao on 10/29/2016.
 */

public class GetTransitList extends AsyncTask<String, Integer, String> {
    public JSONJourney myTravel;
    private StringBuilder response;
    private ListOfRoutesActivity listOfRoutesActivity;
    public GetTransitList(ListOfRoutesActivity activity){
        super();
        listOfRoutesActivity = activity;
    }
    @Override
    protected String doInBackground(String... params) {
        StringBuilder requestURL = new StringBuilder();
        requestURL.append("https://maps.googleapis.com/maps/api/directions/json?"); // link
        requestURL.append("mode=transit");
        requestURL.append("&alternatives=true");
        requestURL.append("&region=vi");
        requestURL.append("&origin=place_id:"+params[0]);
        requestURL.append("&destination=place_id:"+params[1]);
        requestURL.append("&key=AIzaSyBld1HWQyzu6y3az2145b-sgKECcyqHa3E");
        HttpURLConnection connection = null;
        URL url = null;
        try{
            url = new URL(requestURL.toString());
        }catch (MalformedURLException e) {
        }
        try{
            connection = (HttpURLConnection) url.openConnection();
        }catch (IOException e) {
        }

        response = new StringBuilder();
        try{
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String strLine = null;

                while ((strLine = input.readLine()) != null) {
                    response.append(strLine);
                }
                Log.d("Get Transit list",response.toString());
                input.close();
            }
        }
        catch(IOException e){

        }
        return response.toString();
    }
    @Override
    protected void onPostExecute(String result){
        try {
            myTravel = new JSONJourney(result);
            AddInfoToLayout();

        } catch (JSONException e) {
            Toast.makeText(listOfRoutesActivity, "Không xác định chuỗi", Toast.LENGTH_SHORT).show();;
        }
    }
    private void AddInfoToLayout(){
        // info
        String startName = myTravel.routes.get(0).legs.get(0).startAddress;
        String endName = myTravel.routes.get(0).legs.get(0).endAddress;
        listOfRoutesActivity.TravelInfo.setText(ShortenAddress(startName)+"\n - "+ ShortenAddress(endName));
        Log.d("Rout: ",myTravel.routes.toString());
        // details routs
        for (JSONRoute route:myTravel.routes) {

            for (JSONLeg leg:route.legs) {
                String title= ShortenAddress(leg.startAddress) + "\n -"
                        + ShortenAddress(leg.endAddress) + "\n"
                        + ((double) leg.Distance / 1000) + " km" + "\n"
                        + ((double) leg.Duration / 3600) + " hr";
                StringBuilder details = new StringBuilder();


                for (JSONStep step : leg.steps) {
                    if(step.TransitDetail!=null){
                        details.append(step.TransitDetail);
                        details.append("\n");
                        details.append("\t\t"+ShortenAddress(step.Instruction));
                        details.append("\n");
                    }
                    else{
                        details.append(ShortenAddress(step.Instruction));
                        details.append("\n");
                    }
                }
                RouteItem item = new RouteItem(listOfRoutesActivity, title, details.toString(),route);
                listOfRoutesActivity.routeItemList.add(item);
                Log.d("Rout: ",title);
            }

        }
        for (RouteItem i:
                listOfRoutesActivity.routeItemList) {
                listOfRoutesActivity.listOfRoutes.addView(i);
        }
    }

    private String ShortenAddress(String address){
        String[] addressParts = address.split(", ");
        return addressParts[0];
    }
}