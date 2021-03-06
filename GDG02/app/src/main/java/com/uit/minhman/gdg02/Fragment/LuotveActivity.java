package com.uit.minhman.gdg02.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.uit.minhman.gdg02.R;
import com.uit.minhman.gdg02.SearchActivity;

/**
 * Created by Minh Man on 10/29/2016.
 */

public class LuotveActivity extends Fragment {
    ListView lstLuotve;
    String[] dsduong;
    ArrayAdapter adap;
    public LuotveActivity() {
    }

    public static LuotveActivity newInstance() {

        Bundle args = new Bundle();

        LuotveActivity fragment = new LuotveActivity();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_luotve, container, false);
        lstLuotve = (ListView) view.findViewById(R.id.listViewLuotve);
        String luotve= SearchActivity.bus.getLuotve();

        dsduong = luotve.split("-");
        adap= new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, dsduong);
        lstLuotve.setAdapter(adap);
//        String strtext = getArguments().getString("luotdi");

        return view;
    }
}
