package com.example.duytungdao.comfybus.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duytungdao.comfybus.model.Journey;

/**
 * Created by VAN THANH on 10/29/2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    static final String DB_NAME = "SaveBus";
    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, 1); // 1: version
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String jour = JourneyRepository.getCreateTableSQL();
        db.execSQL(jour);
        String place = PlaceRepository.getCreateTableSQL();
        db.execSQL(place);
        String route = RouteRepository.getCreateTableSQL();
        db.execSQL(route);
        String step = StepRepository.getCreateTableSQL();
        db.execSQL(step);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
