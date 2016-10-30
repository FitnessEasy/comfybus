package com.uit.minhman.gdg02;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Minh Man on 10/29/2016.
 */

public class SettingActivity  extends AppCompatActivity {
    ListView lstSetting;
    List<String> dsSet;
    ArrayAdapter adapter;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.language_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = info.position;
        String i = (String) adapter.getItem(pos);
        switch (item.getItemId()){
            case R.id.itemViet:
                Toast.makeText(this, "Ngôn ngữ Tiếng Việt đã được chọn", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itemEnglish:
                Toast.makeText(this, "Ngôn ngữ Tiếng Anh đã được chọn", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Init();

        lstSetting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:

                        break;
                    case 1:
                        Intent intent = new Intent(SettingActivity.this, AppInfoActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });


    }

    private void Init(){
        setContentView(R.layout.setting_layout);
        lstSetting = (ListView) findViewById(R.id.listviewSetting);
        String[] languages = getResources().getStringArray(R.array.settings);
        dsSet = new ArrayList<String>();
        dsSet.addAll(Arrays.asList(languages));
        adapter = new ArrayAdapter(SettingActivity.this, android.R.layout.simple_list_item_1, dsSet);
        lstSetting.setAdapter(adapter);
        registerForContextMenu(lstSetting);
    }
}
