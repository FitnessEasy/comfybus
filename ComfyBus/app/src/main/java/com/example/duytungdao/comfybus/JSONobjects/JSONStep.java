package com.example.duytungdao.comfybus.JSONobjects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by duy tung dao on 10/29/2016.
 */

public class JSONStep {
    public int Distance;
    public int Duration;
    public double[] EndLocation;
    public String Instruction;
    public double[] StartLocation;
    public String TransitDetail;
    public String TravelMode;
    public JSONStep(JSONObject inputJSON)throws JSONException {
        Distance = inputJSON.getJSONObject("distance").getInt("value");
        Duration = inputJSON.getJSONObject("duration").getInt("value");

        EndLocation = new double[2];
        EndLocation[0] = inputJSON.getJSONObject("end_location").getDouble("lat");
        EndLocation[1] = inputJSON.getJSONObject("end_location").getDouble("lng");

        StartLocation = new double[2];
        StartLocation[0] = inputJSON.getJSONObject("start_location").getDouble("lat");
        StartLocation[1] = inputJSON.getJSONObject("start_location").getDouble("lng");

        //html_instructions
        Instruction = inputJSON.getString("html_instructions");
        TravelMode = inputJSON.getString("travel_mode");
        try{
            // get name of bus
            TransitDetail = inputJSON.getJSONObject("transit_details").getJSONObject("line").getString("name");
        }
        catch (JSONException e){
            TransitDetail = null;
        }

    }
}
