package com.example.duytungdao.comfybus.JSONobjects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duy tung dao on 10/29/2016.
 */

public class JSONJourney {
    public String StartPlaceID;
    public String DestinationID;
    public List<JSONRoute> routes;
    public JSONJourney(String input) throws JSONException {
        JSONObject jsonObject = new JSONObject(input);
        // get start id and destination id
        JSONArray array =  jsonObject.getJSONArray("geocoded_waypoints");
        for (int i=0;i<array.length();i++){
            JSONObject j = array.getJSONObject(i);
            if(i==0)
                StartPlaceID = j.getString("place_id");
            else if(i==1)
                DestinationID = j.getString("place_id");
        }

        // get array json of routes
        JSONArray myroutes = jsonObject.getJSONArray("routes");
        // get routes
        routes = GetListOfRoutes(myroutes);
    }
    private ArrayList<JSONRoute> GetListOfRoutes(JSONArray inputJSONArray) throws JSONException {
        ArrayList<JSONRoute> myroutes = new ArrayList<>();
        for (int i=0;i<inputJSONArray.length();i++){
            JSONObject route = inputJSONArray.getJSONObject(i);
            JSONRoute k = new JSONRoute(route);
            myroutes.add(k);
        }
        return myroutes;
    }
}
