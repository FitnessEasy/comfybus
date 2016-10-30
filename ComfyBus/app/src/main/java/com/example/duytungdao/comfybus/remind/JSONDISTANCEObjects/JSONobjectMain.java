package com.example.duytungdao.comfybus.remind.JSONDISTANCEObjects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by duy tung dao on 10/30/2016.
 */

public class JSONobjectMain {
    public ArrayList<JSONobjectStart> starts;
    public ArrayList<JSONobjectDestination>  destinations;
    public ArrayList<JSONobjectElement>  elements;
    public JSONobjectMain(String input)throws JSONException {
        starts = new ArrayList<>();
        destinations = new ArrayList<>();
        elements = new ArrayList<>();
        JSONObject j = new JSONObject(input);
        JSONArray s = j.getJSONArray("origin_addresses");
        for (int i = 0;i<s.length();i++){
            JSONObject k = s.getJSONObject(i);
            JSONobjectStart st = new JSONobjectStart(k);
            starts.add(st);
        }

        JSONArray des = j.getJSONArray("destination_addresses");
        for (int i = 0;i<des.length();i++){
            JSONObject k = des.getJSONObject(i);
            JSONobjectDestination d = new JSONobjectDestination(k);
            destinations.add(d);
        }

        JSONArray rows = j.getJSONArray("rows");
        for (int i = 0;i<rows.length();i++){
            JSONArray k = rows.getJSONArray(i);
            for (int h = 0;h<k.length();h++){
                JSONObject obj = k.getJSONObject(i);
                JSONArray arr = obj.getJSONArray("elements");
                for (int n = 0;n<arr.length();n++) {
                    JSONobjectElement e = new JSONobjectElement(arr.getJSONObject(n));
                    elements.add(e);
                }
            }
        }
    }
}
