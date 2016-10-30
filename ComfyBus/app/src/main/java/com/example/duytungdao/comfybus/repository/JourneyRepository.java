package com.example.duytungdao.comfybus.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.duytungdao.comfybus.model.Journey;

/**
 * Created by VAN THANH on 10/29/2016.
 */

public class JourneyRepository {
    static final String TAB_JOURNEY = "Journey";
    static final String COL_JOURNEYID = "JourneyID";
    static final String COL_STARTPLACEID = "StartPlaceID";
    static final String COL_DESTINATIONID = "DestinationID";
    static final String COL_CREATETIME ="CreateTime";

    public static String getCreateTableSQL()
    {
        String sql = "CREATE TABLE " + TAB_JOURNEY + "(" +
                COL_JOURNEYID + " integer primary key autoincrement, " +
                COL_STARTPLACEID + " integer, " +
                COL_DESTINATIONID + " integer, " +
                COL_CREATETIME + " text)";
        return sql;
    }

    private DatabaseHandler databaseHandler;
    public JourneyRepository(Context context) {
        databaseHandler = new DatabaseHandler(context);
    }

    private ContentValues MakeJourneyContentValues(Journey journey)
    {
        ContentValues cv = new ContentValues();
        cv.put(COL_STARTPLACEID, journey.getStartPlaceID());
        cv.put(COL_DESTINATIONID, journey.getDestinationID());
        cv.put(COL_CREATETIME, journey.getCreateTime());
        return cv;
    }

    public void insertJourney(Journey p)
    {
        ContentValues cv = MakeJourneyContentValues(p);
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        int id = (int) db.insert(TAB_JOURNEY, null, cv);
        if (id!= -1) p.setJourneyID(id);
    }

    public void updateJourney(Journey p)
    {
        ContentValues cv = MakeJourneyContentValues(p);
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.update(TAB_JOURNEY, cv, COL_JOURNEYID + "=?",
                new String[] { String.valueOf(p.getJourneyID()) });
    }

    public void deleteJourney(int id)
    {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.delete(TAB_JOURNEY,COL_JOURNEYID + " = " + id,null);
    }

    public ArrayList<Journey> getAllProduct()
    {
        ArrayList<Journey> journeys = new ArrayList<Journey>();
        SQLiteDatabase db = databaseHandler.getReadableDatabase();
        String sql = "SELECT * FROM " + TAB_JOURNEY;
        Cursor cursor = db.rawQuery(sql, null);
        int indexJourneyID = cursor.getColumnIndex(COL_JOURNEYID);
        int indexStartPlaceID = cursor.getColumnIndex(COL_STARTPLACEID);
        int indexDestination = cursor.getColumnIndex(COL_DESTINATIONID);
        int indexCreateTime = cursor.getColumnIndex(COL_CREATETIME);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Journey p = new Journey();
                p.setJourneyID(cursor.getInt(indexJourneyID));
                p.setStartPlaceID(cursor.getInt(indexStartPlaceID));
                p.setDestinationID(cursor.getInt(indexDestination));
                p.setCreateTime(cursor.getString(indexCreateTime));
                journeys.add(p);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return journeys;
    }
    public int getLatestJourneyID(){
        int i=0;
        String sql = "SELECT "+ COL_JOURNEYID+" FROM " + TAB_JOURNEY;
        SQLiteDatabase db = databaseHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int indexPlaceSystemID = cursor.getColumnIndex(COL_JOURNEYID);
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
