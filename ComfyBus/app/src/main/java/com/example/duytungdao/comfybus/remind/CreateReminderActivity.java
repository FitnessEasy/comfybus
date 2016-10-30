package com.example.duytungdao.comfybus.remind;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.duytungdao.comfybus.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.io.Serializable;

/**
 * Created by duy tung dao on 10/29/2016.
 */

public class CreateReminderActivity extends AppCompatActivity implements Serializable {
    private PlaceAutocompleteFragment original_autocomplete_fragment;
    private PlaceAutocompleteFragment destination_autocomplete_fragment;

    public static Place original;
    public static Place destination;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_reminder);
        original = null;
        destination = null;
        InitComponents();
    }

    public void onBtnClick(View v){
        switch(v.getId()){
            case R.id.btn_GetListOfRoutes:
                if(original!=null&&destination!=null){
                    Intent intent = new Intent(CreateReminderActivity.this,ListOfRoutesActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(this, "Nhập đầy đủ điểm đi và điểm đến", Toast.LENGTH_LONG).show();
                break;
        }
    }
    private void InitComponents(){
        original_autocomplete_fragment = (PlaceAutocompleteFragment)getFragmentManager()
                .findFragmentById(R.id.original_autocomplete_fragment);
        destination_autocomplete_fragment = (PlaceAutocompleteFragment)getFragmentManager()
                .findFragmentById(R.id.destination_autocomplete_fragment);

        original_autocomplete_fragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                original = place;
            }

            @Override
            public void onError(Status status) {
                Log.d("Eror",status.toString());
                Toast.makeText(CreateReminderActivity.this, status.toString(), Toast.LENGTH_LONG).show();
            }
        });

        destination_autocomplete_fragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                destination = place;
            }

            @Override
            public void onError(Status status) {
                Toast.makeText(CreateReminderActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
