package com.uit.minhman.gdg02;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uit.minhman.gdg02.Adapter.BusAdapter;
import com.uit.minhman.gdg02.Model.Bus_Info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minh Man on 10/29/2016.
 */

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    ListView lstBus;
    List<String> dsbuyt;

    BusAdapter adapter;
    SearchView searchView;
    static DatabaseReference mData;//note goc
    ArrayList<Bus_Info> arrayBus, tempBus, luuBus;
    public static Bus_Info bus;
//    ArrayList<String> dsMasotuyen, dsLuotdi, dsLuotve;
//    List<Integer> dsPositionIndex;

    static
    {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mData = FirebaseDatabase.getInstance().getReference();
        mData.keepSynced(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Init();

//        for (int i=11;i<153;i++){
//            Bus_Info bus_info = new Bus_Info();
//            bus_info.masotuyen= i+"";
//            mData.child("Buyt").push().setValue(bus_info);
//
//        }

        mData.child("Buyt").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Bus_Info bus = dataSnapshot.getValue(Bus_Info.class);
                arrayBus.add(bus);
                luuBus.add(bus);
                dsbuyt.add(bus.getMasotuyen());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lstBus.setTextFilterEnabled(true);
        lstBus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bus = arrayBus.get(position);
                Intent intent = new Intent(SearchActivity.this, DetailBusActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bus", arrayBus.get(position));
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });
//        for (Bus_Info bus : arrayBus){
//            dsMasotuyen.add(bus.getMasotuyen());
//            dsLuotdi.add(bus.getLuotdi());
//            dsLuotve.add(bus.getLuotve());
//        }



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timkiem,menu);
        MenuItem itemSearch =menu.findItem(R.id.itSearch);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) MenuItemCompat.getActionView(itemSearch);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setIconified(true);
        searchView.setOnQueryTextListener(this);

        return true;
    }


    private void Init(){
        setContentView(R.layout.search_layout);
        arrayBus = new ArrayList<Bus_Info>();
        dsbuyt = new ArrayList<String>();
        lstBus = (ListView)findViewById(R.id.listviewData);
        adapter = new BusAdapter(SearchActivity.this, R.layout.custom_listview_bus, arrayBus);
        lstBus.setAdapter(adapter);
        bus = new Bus_Info();
        luuBus = new ArrayList<Bus_Info>();
//        dsPositionIndex = new ArrayList<Integer>();
//        dsMasotuyen = new ArrayList<String>();
//        dsLuotdi = new ArrayList<String>();
//        dsLuotve = new ArrayList<String>();
        tempBus = new ArrayList<Bus_Info>();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchView.setQuery(String.valueOf(query), false);
            Toast.makeText(this,"Từ khóa: " + query+" đang tìm kiếm...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.toString()== null){
            for (Bus_Info b : luuBus){
                arrayBus.add(b);
            }
            adapter.notifyDataSetChanged();
        }
        ArrayList<Bus_Info> bus = new ArrayList<>();
        for (Bus_Info info : luuBus){
            if(info.getMasotuyen().contains(newText) || info.getLuotdi().contains(newText) || info.getLuotve().contains(newText))
                bus.add(info);
        }
        adapter = new BusAdapter(SearchActivity.this, R.layout.custom_listview_bus, bus);
        lstBus.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        arrayBus.clear();
        for (Bus_Info b : bus){
            arrayBus.add(b);
        }
       return true;
    }
}