package com.example.duytungdao.comfybus.JSONobjects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by duy tung dao on 10/29/2016.
 */

public class JSONLeg {
    public int Distance;
    public int Duration;
    public double[] startLocation;
    public double[] endLocation;
    public String startAddress;
    public String endAddress;
    public List<JSONStep> steps;
    public JSONLeg(JSONObject inputJSON)throws JSONException {
        Distance = inputJSON.getJSONObject("distance").getInt("value");
        Duration = inputJSON.getJSONObject("duration").getInt("value");
        startAddress = inputJSON.getString("start_address");
        endAddress = inputJSON.getString("end_address");

        startLocation = new double[2];
        startLocation[0] = inputJSON.getJSONObject("start_location").getDouble("lat");
        startLocation[1] = inputJSON.getJSONObject("start_location").getDouble("lng");

        endLocation = new double[2];
        endLocation[0] = inputJSON.getJSONObject("end_location").getDouble("lat");
        endLocation[1] = inputJSON.getJSONObject("end_location").getDouble("lng");

        JSONArray jsteps=  inputJSON.getJSONArray("steps");
        steps = GetSteps(jsteps);
    }
    private ArrayList<JSONStep> GetSteps(JSONArray array)throws JSONException {
        ArrayList<JSONStep> list= new ArrayList<>();
        for (int i=0;i<array.length();i++){
            JSONObject k = array.getJSONObject(i);
            JSONStep step = new JSONStep(k);
            list.add(step);
        }
        return list;
    }
}
