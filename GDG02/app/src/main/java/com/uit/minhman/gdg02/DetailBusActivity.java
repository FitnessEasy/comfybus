package com.uit.minhman.gdg02;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.uit.minhman.gdg02.Adapter.ViewPagerAdapter;

/**
 * Created by Minh Man on 10/29/2016.
 */

public class DetailBusActivity extends AppCompatActivity {
    ViewPagerAdapter mViewPagerAdapter;
    ViewPager mViewPager;
    TabLayout tabLayout;
    //Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Init();
        mViewPager.setAdapter(mViewPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private void Init(){
        setContentView(R.layout.detail_bus_layout);
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPagerAdapter= new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager=(ViewPager)findViewById(R.id.Viewpagerdetail);

    }
}

