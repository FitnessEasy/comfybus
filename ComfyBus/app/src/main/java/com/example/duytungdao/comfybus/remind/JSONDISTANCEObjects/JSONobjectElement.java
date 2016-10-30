package com.example.duytungdao.comfybus.remind.JSONDISTANCEObjects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by duy tung dao on 10/30/2016.
 */

public class JSONobjectElement {
    public int Distance;
    public JSONobjectElement(JSONObject input) throws JSONException {
        Distance = input.getJSONObject("distance").getInt("value");
    }
}
