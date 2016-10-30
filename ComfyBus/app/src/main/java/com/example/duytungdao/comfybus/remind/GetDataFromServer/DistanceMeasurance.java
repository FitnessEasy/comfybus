package com.example.duytungdao.comfybus.remind.GetDataFromServer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.duytungdao.comfybus.JSONobjects.JSONJourney;
import com.example.duytungdao.comfybus.remind.GPSTracker;
import com.example.duytungdao.comfybus.remind.JSONDISTANCEObjects.JSONobjectElement;
import com.example.duytungdao.comfybus.remind.JSONDISTANCEObjects.JSONobjectMain;
import com.example.duytungdao.comfybus.remind.ListOfRoutesActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by duy tung dao on 10/30/2016.
 */

public class DistanceMeasurance extends AsyncTask<String,String,String> {
    public GPSTracker tracker;
    public DistanceMeasurance(GPSTracker track){
        super();
        tracker = track;
    }
    @Override
    protected String doInBackground(String... params) {
        StringBuilder requestURL = new StringBuilder();
        requestURL.append("https://maps.googleapis.com/maps/api/distancematrix/json?"); // link
        requestURL.append("mode=transit");
        requestURL.append("&origin="+params[0]);
        requestURL.append("&destination="+params[1]);
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

        StringBuilder response = new StringBuilder();
        try{
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String strLine = null;

                while ((strLine = input.readLine()) != null) {
                    response.append(strLine);
                }
                input.close();
            }
        }
        catch(IOException e){

        }
        return response.toString();
    }
    @Override
    protected void onPostExecute(String result){
        Toast.makeText(tracker.activity, "asdfasdfaf", Toast.LENGTH_SHORT).show();
        JSONobjectMain main = null;
        try{
            main = new JSONobjectMain(result);
        }catch (JSONException e){

        }
        ArrayList<Integer> distances = new ArrayList<>();
        for (JSONobjectElement e:main.elements
             ) {
            //if distance lower than 400 meters
            if(e.Distance< 400){
                Toast.makeText(tracker.activity, "Gần tới rồi", Toast.LENGTH_LONG).show();
            }
        }
    }
}
