package com.example.duytungdao.comfybus.remind;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.duytungdao.comfybus.MainActivity;
import com.example.duytungdao.comfybus.R;
/**
 * Created by duy tung dao on 10/29/2016.
 */

public class ListJourneyActivity extends AppCompatActivity {
    private Button btn_AddNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_journey);
        btn_AddNew = (Button) findViewById(R.id.btn_AddNew);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.remind_top_menu, menu);
        return true;
    }
    public void onBtnClick(View v){
        switch(v.getId()){
            case R.id.btn_AddNew:
                Intent intent = new Intent(ListJourneyActivity.this, CreateReminderActivity.class);
                startActivity(intent);
                break;
        }
    }

}