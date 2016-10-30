package com.example.duytungdao.comfybus.remind.GetDataFromServer;

import com.example.duytungdao.comfybus.model.*;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by duy tung dao on 10/29/2016.
 */

public class JSONTransitList {
    public Journey journey;
    public List<Place> place;
    public List<Route> routes;
    public List<Step> steps;
    public JSONTransitList(String JSONobject){

    }
}
