package com.example.duytungdao.comfybus.model;

/**
 * Created by VAN THANH on 10/29/2016.
 */

public class Step {
    public int StepID;
    public int Distance;
    public int Duration;
    public double StartLat;
    public double StartLng;
    public double EndLat;
    public double EndLng;
    public String Guide;
    public String TravelMode;
    public int RouteID;
    public int IsPassed;

    public Step(int stepID,int distance, int duration, double startLat, double startLng, double endLat, double endLng,
                String guide,
                String travelMode, int routeID, int isPassed){
        super();
        this.StepID = stepID;
        this.Distance = distance;
        this.Duration = duration;
        this.StartLat = startLat;
        this.StartLat = startLng;
        this.EndLat = endLat;
        this.EndLng = endLng;
        this.Guide = guide;
        this.TravelMode = travelMode;
        this.RouteID = routeID;
        this.IsPassed = isPassed;
    }

    public Step(int distance, int duration, double startLat, double startLng, double endLat, double endLng,
                String guide,
                String travelMode,
                int routeID,
                int isPassed){
        super();
        this.Distance = distance;
        this.Duration = duration;
        this.StartLat = startLat;
        this.StartLat = startLng;
        this.EndLat = endLat;
        this.EndLng = endLng;
        this.Guide = guide;
        this.TravelMode = travelMode;
        this.RouteID = routeID;
        this.IsPassed = isPassed;
    }
    public Step() {

    }
}
