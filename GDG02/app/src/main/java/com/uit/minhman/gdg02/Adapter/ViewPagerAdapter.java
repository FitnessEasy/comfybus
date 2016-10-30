package com.uit.minhman.gdg02.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.uit.minhman.gdg02.Fragment.ChitietActivity;
import com.uit.minhman.gdg02.Fragment.LuotdiActivity;
import com.uit.minhman.gdg02.Fragment.LuotveActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minh Man on 10/29/2016.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> listFragment = new ArrayList<Fragment>();
    List<String> titleFragment = new ArrayList<String>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        listFragment.add(new LuotdiActivity());
        listFragment.add(new LuotveActivity());
        listFragment.add(new ChitietActivity());

        titleFragment.add("Lượt đi");
        titleFragment.add("Lượt về");
        titleFragment.add("Thông tin khác");
    }
    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }
}
