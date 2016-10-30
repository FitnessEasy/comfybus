package com.uit.minhman.gdg02.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uit.minhman.gdg02.Model.Bus_Info;
import com.uit.minhman.gdg02.R;

import java.util.List;

/**
 * Created by Minh Man on 10/29/2016.
 */

public class BusAdapter extends ArrayAdapter<Bus_Info> {
    private Activity activity;
    private int layout;
    private List<Bus_Info> list;
    private List<Bus_Info> ds;
    public BusAdapter(Activity activity, int newlayout, List<Bus_Info> list) {
        super(activity, newlayout, list);
        this.activity = activity;
        this.layout = newlayout;
        this.list = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater infLater = activity.getLayoutInflater();
        convertView = infLater.inflate(layout,null);
        final ViewHolder holder = new ViewHolder();
        holder.masotuyen = (TextView) convertView.findViewById(R.id.textViewMaso);
        holder.tentuyen = (TextView) convertView.findViewById(R.id.textViewTuyen);

        holder.masotuyen.setText(list.get(position).getMasotuyen());
        holder.tentuyen.setText(list.get(position).getTentuyen());
        convertView.setTag(holder);
        return convertView;
    }

    static class ViewHolder {
        RelativeLayout relativeLayoutLayout;
        TextView masotuyen;
        TextView tentuyen;
    }
}
