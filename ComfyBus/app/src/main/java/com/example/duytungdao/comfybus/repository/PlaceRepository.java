package com.example.duytungdao.comfybus.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.duytungdao.comfybus.model.Place;
import com.example.duytungdao.comfybus.model.Route;

/**
 * Created by VAN THANH on 10/29/2016.
 */

public class PlaceRepository {
    static final String TAB_PLACE = "Place";
    static final String COL_PLACESYSTEMID = "PlaceSystemID";
    static final String COL_PLACEID = "PlaceID";
    static final String COL_LAT = "Lat";
    static final String COL_LNG ="Lng";
    static final String COL_PLACENAME = "PlaceName";

    public static String getCreateTableSQL()
    {
        String sql = "CREATE TABLE " + TAB_PLACE + "(" +
                COL_PLACESYSTEMID + " integer primary key autoincrement, " +
                COL_PLACEID + " text, " +
                COL_LAT + " real, " +
                COL_LNG + " real, " +
                COL_PLACENAME + " text,  )";
        return sql;
    }

    private DatabaseHandler databaseHandler;
    public PlaceRepository(Context context) {
        databaseHandler = new DatabaseHandler(context);
    }

    private ContentValues MakePlaceContentValues(Place p)
    {
        ContentValues cv = new ContentValues();
        cv.put(COL_PLACEID, p.getPlaceID());
        cv.put(COL_LAT, p.getLat());
        cv.put(COL_LNG, p.getLng());
        cv.put(COL_PLACENAME, p.getPlaceName());
        return cv;
    }

    public void insertPlace(Place p)
    {
        ContentValues cv = MakePlaceContentValues(p);
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        int id = (int) db.insert(TAB_PLACE, null, cv);
        if (id!= -1) p.setPlaceSystemID(id);
    }

    public void updatePlace(Place p)
    {
        ContentValues cv = MakePlaceContentValues(p);
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.update(TAB_PLACE, cv, COL_PLACESYSTEMID + "=?",
                new String[] { String.valueOf(p.getPlaceSystemID()) });
    }

    public void deletePlace(int id)
    {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.delete(TAB_PLACE,COL_PLACESYSTEMID + " = " + id,null);
    }

    public ArrayList<Place> getAllPlace()
    {
        ArrayList<Place> places = new ArrayList<Place>();
        SQLiteDatabase db = databaseHandler.getReadableDatabase();
        String sql = "SELECT * FROM " + TAB_PLACE;
        Cursor cursor = db.rawQuery(sql, null);
        int indexPlaceSystemID = cursor.getColumnIndex(COL_PLACESYSTEMID);
        int indexPlaceID = cursor.getColumnIndex(COL_PLACEID);
        int indexLat = cursor.getColumnIndex(COL_LAT);
        int indexLng = cursor.getColumnIndex(COL_LNG);
        int indexPlaceName = cursor.getColumnIndex(COL_PLACENAME);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Place p = new Place();
                p.setPlaceSystemID(cursor.getInt(indexPlaceSystemID));
                p.setPlaceID(cursor.getString(indexPlaceID));
                p.setLat(cursor.getDouble(indexLat));
                p.setLng(cursor.getDouble(indexLng));
                p.setPlaceName(cursor.getString(indexPlaceName));
                places.add(p);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return places;
    }
    public int getLatestPlaceID(){
        int i=0;
        String sql = "SELECT "+ COL_PLACESYSTEMID+" FROM " + TAB_PLACE;
        SQLiteDatabase db = databaseHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int indexPlaceSystemID = cursor.getColumnIndex(COL_PLACESYSTEMID);
        ArrayList<Integer> listids = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                listids.add(cursor.getInt(indexPlaceSystemID));
                cursor.moveToNext();
            }
        }
        for (Integer k:listids
             ) {
            if (i<k)
                i=k;
        }
        return i;
    }
}
