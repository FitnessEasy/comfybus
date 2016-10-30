package com.example.duytungdao.comfybus.model;

/**
 * Created by VAN THANH on 10/29/2016.
 */

public class Journey {
    private int JourneyID;
    private int StartPlaceID;
    private int DestinationID;
    private String CreateTime;

    public Journey(int id, int startplaceid, int destinationid, String createtime)
    {
        super();
        this.JourneyID = id;
        this.StartPlaceID = startplaceid;
        this.DestinationID = destinationid;
        this.CreateTime = createtime;
    }

    public Journey(int startplaceid, int destinationid, String createtime)
    {
        super();
        this.StartPlaceID = startplaceid;
        this.DestinationID = destinationid;
        this.CreateTime = createtime;
    }

    public Journey() {

    }

    public int getJourneyID()
    {
        return JourneyID;
    }

    public void setJourneyID(int journeyID){
        this.JourneyID = journeyID;
    }

    public int getStartPlaceID()
    {
        return StartPlaceID;
    }

    public void setStartPlaceID(int startPlaceID){
        this.StartPlaceID = startPlaceID;
    }

    public int getDestinationID(){
        return DestinationID;
    }

    public void setDestinationID(int destinationID){
        this.DestinationID = destinationID;
    }

    public String getCreateTime(){
        return CreateTime;
    }

    public void setCreateTime(String createTime){
        this.CreateTime = createTime;
    }

}
