package com.uit.minhman.gdg02.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uit.minhman.gdg02.R;
import com.uit.minhman.gdg02.SearchActivity;

/**
 * Created by Minh Man on 10/29/2016.
 */

public class ChitietActivity extends Fragment {
    TextView txtmasotuyen, txtTentuyen, txtThoigianchuyen, txtCuly, txtLoaixe;
    TextView txtThoigianhoatdong, txtGiancach, txtSochuyen, txtLoaihinhhoatdong;
    public ChitietActivity() {
    }

    public static ChitietActivity newInstance() {

        Bundle args = new Bundle();

        ChitietActivity fragment = new ChitietActivity();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chitiet,container, false);
        txtmasotuyen = (TextView) view.findViewById(R.id.textmasotuyen);
        txtTentuyen = (TextView) view.findViewById(R.id.textTentuyen);
        txtThoigianchuyen = (TextView) view.findViewById(R.id.textThoigiantuyen);
        txtCuly = (TextView) view.findViewById(R.id.textCuly);
        txtLoaixe =  (TextView) view.findViewById(R.id.textLoaixe);
        txtThoigianhoatdong = (TextView) view.findViewById(R.id.textThoigianhoatdong);
        txtGiancach = (TextView) view.findViewById(R.id.textGiancach);
        txtSochuyen =  (TextView) view.findViewById(R.id.textSochuyen);
        txtLoaihinhhoatdong =  (TextView) view.findViewById(R.id.textLoaihinhhoatdong);

        txtmasotuyen.setText(SearchActivity.bus.getMasotuyen());
        txtTentuyen.setText(SearchActivity.bus.getTentuyen());
        txtThoigianchuyen.setText(SearchActivity.bus.getThoigianchuyen());
        txtCuly.setText(SearchActivity.bus.getCuly());
        txtLoaixe.setText(SearchActivity.bus.getLoaixe());
        txtThoigianhoatdong.setText(SearchActivity.bus.getThoigianhoatdong());
        txtGiancach.setText(SearchActivity.bus.getGiancach());
        txtSochuyen.setText(SearchActivity.bus.getSochuyentrongngay());
        txtLoaihinhhoatdong.setText(SearchActivity.bus.getLoaihinhhoatdong());
        return view;
    }
}

