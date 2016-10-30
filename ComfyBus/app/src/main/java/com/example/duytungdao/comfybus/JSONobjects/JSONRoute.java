package com.example.duytungdao.comfybus.JSONobjects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duy tung dao on 10/29/2016.
 */

public class JSONRoute {
    public List<JSONLeg> legs;
    public JSONRoute(JSONObject JSONobject)throws JSONException {
        JSONArray jlegs = JSONobject.getJSONArray("legs");
        legs = GetListOfLegs(jlegs);
    }
    private ArrayList<JSONLeg> GetListOfLegs(JSONArray legslist) throws JSONException {
        ArrayList<JSONLeg> list = new ArrayList<>();
        for (int i=0;i<legslist.length();i++){
            JSONObject k = legslist.getJSONObject(i);
            JSONLeg leg = new JSONLeg(k);
            list.add(leg);
        }
        return list;
    }
}
