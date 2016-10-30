package com.example.duytungdao.comfybus.remind.controls;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.example.duytungdao.comfybus.JSONobjects.JSONLeg;
import com.example.duytungdao.comfybus.JSONobjects.JSONRoute;
import com.example.duytungdao.comfybus.R;

import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by duy tung dao on 10/29/2016.
 */

public class RouteItem extends LinearLayout {
    public LinearLayout TitleLinear;          // visible bar, including checkbox
    public JSONRoute MyRout;
    public CheckBox box;
    private LinearLayout DetailsBox;
    private TextView Details;       // invisible linear layout
    public RouteItem(Context context, String title, String details,JSONRoute rout){
        super(context);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(0,4,0,4);
        this.setLayoutParams(params);
        this.setBackgroundColor(0xfff00000);
        this.setOrientation(VERTICAL);

        MyRout = rout;

        SetComponents(context,title, details);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DetailsBox.getVisibility() == GONE)
                    DetailsBox.setVisibility(VISIBLE);
                else
                    DetailsBox.setVisibility(GONE);
            }
        });
    }
    private void SetComponents(Context context, String title, String details){

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        box = new CheckBox(context);
        box.setText(title);
        box.setLayoutParams(params);

        LayoutParams DetailsParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
        Details = new TextView(context);
        DetailsParams.weight = 4;
        Details.setText(details);
        Details.setLayoutParams(DetailsParams);

        LayoutParams SpaceParams = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        Space space = new Space(context);
        SpaceParams.weight = 1;


        LayoutParams paramsDetailBox = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        DetailsBox = new LinearLayout(context);
        DetailsBox.setLayoutParams(paramsDetailBox);
        DetailsBox.setOrientation(HORIZONTAL);
        DetailsBox.setVisibility(GONE);
        DetailsBox.addView(space);
        DetailsBox.addView(Details);

        TitleLinear = new LinearLayout(context);
        TitleLinear.setOrientation(LinearLayout.HORIZONTAL);
        TitleLinear.addView(box);
        TitleLinear.setLayoutParams(params);

        this.addView(TitleLinear);
        this.addView(DetailsBox);
    }
}
