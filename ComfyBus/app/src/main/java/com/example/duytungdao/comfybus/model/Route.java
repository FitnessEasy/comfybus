package com.example.duytungdao.comfybus.model;

/**
 * Created by VAN THANH on 10/29/2016.
 */

public class Route {
    private int RouteID;
    private int Distance;
    private int Duration;
    private int JourneyID;

    public Route(int routeID,  int distance, int duration, int journeyID)
    {
        super();
        this.RouteID = routeID;
        this.Distance = distance;
        this.Duration = duration;
        this.JourneyID = journeyID;
    }

    public Route( int distance, int duration, int journeyID)
    {
        super();
        this.Distance = distance;
        this.Duration = duration;
        this.JourneyID = journeyID;
    }

    public Route() {

    }

    public int getRouteID(){
        return RouteID;
    }


    public int getDistance(){
        return Distance;
    }

    public void setDistance(int distance){
        this.Distance = distance;
    }

    public int getDuration(){
        return Duration;
    }

    public void setDuration(int duration){
        this.Duration = duration;
    }

    public int getJourneyID(){
        return JourneyID;
    }

    public void setJourneyID(int journeyID){
        this.JourneyID = journeyID;
    }

    public void setRouteID(int routeID) { this.RouteID = routeID;
    }
}
