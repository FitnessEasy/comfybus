package com.example.duytungdao.comfybus.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.duytungdao.comfybus.model.Journey;
import com.example.duytungdao.comfybus.model.Route;
import com.example.duytungdao.comfybus.model.Step;

/**
 * Created by VAN THANH on 10/29/2016.
 */

public class RouteRepository {
    static final String TAB_ROUTE = "Route";
    static final String COL_ROUTEID = "RouteID";
    static final String COL_DISTANCE ="Distance";
    static final String COL_DURATION = "Duration";
    static final String COL_JOURNEYID = "JourneyID";

    public static String getCreateTableSQL()
    {
        String sql = "CREATE TABLE " + TAB_ROUTE + "(" +
                COL_ROUTEID + " integer primary key autoincrement, " +
                COL_DISTANCE + " integer, " +
                COL_DURATION + " integer, " +
                COL_JOURNEYID + "integer, )";
        return sql;
    }

    private DatabaseHandler databaseHandler;
    public RouteRepository(Context context) {
        databaseHandler = new DatabaseHandler(context);
    }

    private ContentValues MakeRouteContentValues(Route r)
    {
        ContentValues cv = new ContentValues();
        cv.put(COL_DISTANCE, r.getDistance());
        cv.put(COL_DURATION, r.getDuration());
        cv.put(COL_JOURNEYID, r.getJourneyID());
        return cv;
    }

    public void insertRoute(Route r)
    {
        ContentValues cv = MakeRouteContentValues(r);
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        int id = (int) db.insert(TAB_ROUTE, null, cv);
        if (id!= -1) r.setJourneyID(id);
    }

    public void updateRoute(Route r)
    {
        ContentValues cv = MakeRouteContentValues(r);
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.update(TAB_ROUTE, cv, COL_ROUTEID + "=?",
                new String[] { String.valueOf(r.getRouteID()) });
    }

    public void deleteRoute(int id)
    {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.delete(TAB_ROUTE,COL_ROUTEID + " = " + id,null);
    }

    public int getLatestRout(){
        int i=0;
        String sql = "SELECT "+ COL_ROUTEID+" FROM " + TAB_ROUTE;
        SQLiteDatabase db = databaseHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int indexPlaceSystemID = cursor.getColumnIndex(COL_ROUTEID);
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
