package com.example.duytungdao.comfybus.model;

/**
 * Created by VAN THANH on 10/29/2016.
 */

public class Place {
    private int PlaceSystemID;
    private String PlaceID;
    private double Lat;
    private double Lng;
    private String PlaceName;

    public Place(int placeSystemID, String placeID, double lat, double lng, String placeName){
        super();
        this.PlaceSystemID = placeSystemID;
        this.PlaceID = placeID;
        this.Lat = lat;
        this.Lng = lng;
        this.PlaceName = placeName;
    }

    public Place(String placeID, double lat, double lng, String placeName){
        super();
        this.PlaceID = placeID;
        this.Lat = lat;
        this.Lng = lng;
        this.PlaceName = placeName;
    }

    public Place() {

    }

    public int getPlaceSystemID(){
        return PlaceSystemID;
    }

    public String getPlaceID(){
        return PlaceID;
    }

    public void setPlaceID(String placeID){
        this.PlaceID = placeID;
    }

    public double getLat(){
        return Lat;
    }

    public void setLat(double lat){
        this.Lat = lat;
    }

    public double getLng(){
        return Lng;
    }

    public void setLng(double lng){
        this.Lng = lng;
    }

    public String getPlaceName(){
        return PlaceName;
    }

    public void setPlaceName(String placeName){
        this.PlaceName = placeName;
    }

    public void setPlaceSystemID(int id) { this.PlaceSystemID = id;
    }

}
