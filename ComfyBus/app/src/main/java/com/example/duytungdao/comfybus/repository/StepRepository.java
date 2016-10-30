package com.example.duytungdao.comfybus.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.duytungdao.comfybus.model.Route;
import com.example.duytungdao.comfybus.model.Step;

/**
 * Created by VAN THANH on 10/29/2016.
 */

public class StepRepository {
    static final String TAB_STEP = "Step";
    static final String COL_STEPID = "StepID";
    static final String COL_DISTANCE = "Distance";
    static final String COL_DURATION = "Duration";
    static final String COL_STARTPLACELAT ="StartPlaceLat";
    static final String COL_STARTPLACELNG ="StartPlaceLng";
    static final String COL_ENDPLACELAT ="EndPlaceLat";
    static final String COL_ENDPLACELNG ="EndPlaceLng";
    static final String COL_GUIDE = "Guide";
    static final String COL_TRAVELMODE = "TravelMode";
    static final String COL_ROUTEID = "RouteID";
    static final String COL_ISPASSED ="IsPassed";

    public static String getCreateTableSQL()
    {
        String sql = "CREATE TABLE " + TAB_STEP + "(" +
                COL_STEPID + " integer primary key autoincrement, " +
                COL_DISTANCE + " integer, " +
                COL_DURATION + " integer, " +
                COL_STARTPLACELAT + " real, " +
                COL_STARTPLACELNG + " real, " +
                COL_ENDPLACELAT + " real, " +
                COL_ENDPLACELNG + " real, " +
                COL_GUIDE + " text, " +
                COL_TRAVELMODE + " text, " +
                COL_ROUTEID + " integer, " +
                COL_ISPASSED + " integer, )";
        return sql;
    }

    private DatabaseHandler databaseHandler;
    public StepRepository(Context context) {
        databaseHandler = new DatabaseHandler(context);
    }

    private ContentValues MakeStepContentValues(Step s)
    {
        ContentValues cv = new ContentValues();
        cv.put(COL_DISTANCE, s.Distance);
        cv.put(COL_DURATION, s.Duration);
        cv.put(COL_STARTPLACELAT, s.StartLat);
        cv.put(COL_STARTPLACELNG, s.StartLng);
        cv.put(COL_ENDPLACELAT, s.EndLat);
        cv.put(COL_ENDPLACELNG, s.EndLng);
        cv.put(COL_GUIDE, s.Guide);
        cv.put(COL_TRAVELMODE, s.TravelMode);
        cv.put(COL_ROUTEID, s.RouteID);
            cv.put(COL_ISPASSED, s.IsPassed);
        return cv;
    }

    public void insertStep(Step s)
    {
        ContentValues cv = MakeStepContentValues(s);
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        int id = (int) db.insert(TAB_STEP, null, cv);
        if (id!= -1) s.StepID = id;
    }

    public void updateStep(Step s)
    {
        ContentValues cv = MakeStepContentValues(s);
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.update(TAB_STEP, cv, COL_STEPID + "=?",
                new String[] { String.valueOf(s.StepID) });
    }

    public void deleteStep(int id)
    {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.delete(TAB_STEP,COL_STEPID + " = " + id,null);
    }

    public ArrayList<Step> GetStepByRoutID(int routID){
        SQLiteDatabase db = databaseHandler.getReadableDatabase();
        ArrayList<Step> steps = new ArrayList<Step>();
        String sql = "SELECT * FROM " + TAB_STEP +"WHERE "+" ROUTID="+routID;
        Cursor cursor = db.rawQuery(sql, null);
        int indexStepID = cursor.getColumnIndex(COL_STEPID);
        int indexDistance = cursor.getColumnIndex(COL_DISTANCE);
        int indexDuration = cursor.getColumnIndex(COL_DURATION);
        int indexStartPlaceLat = cursor.getColumnIndex(COL_STARTPLACELAT);
        int indexStartPlaceLng = cursor.getColumnIndex(COL_STARTPLACELNG);
        int indexEndPlaceLat = cursor.getColumnIndex(COL_ENDPLACELAT);
        int indexEndPlaceLng = cursor.getColumnIndex(COL_ENDPLACELNG);
        int indexGuide = cursor.getColumnIndex(COL_GUIDE);
        int indexTravelMode = cursor.getColumnIndex(COL_TRAVELMODE);
        int indexRouteID = cursor.getColumnIndex(COL_ROUTEID);
        int indexIsPassed = cursor.getColumnIndex(COL_ISPASSED);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Step s = new Step(
                        cursor.getInt(indexStepID),
                        cursor.getInt(indexDistance),
                        cursor.getInt(indexDuration),
                        cursor.getDouble(indexStartPlaceLat),
                        cursor.getDouble(indexStartPlaceLng),
                        cursor.getDouble(indexEndPlaceLat),
                        cursor.getDouble(indexEndPlaceLng),
                        cursor.getString(indexGuide),
                        cursor.getString(indexTravelMode),
                        cursor.getInt(indexRouteID),
                        cursor.getInt(indexIsPassed));
                steps.add(s);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return steps;
    }
}
