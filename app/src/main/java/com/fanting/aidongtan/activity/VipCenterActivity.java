package com.fanting.aidongtan.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by foryoung on 2017/12/29.
 */

public class VipCenterActivity extends Activity {


    private List<String> types = new ArrayList<>() ;

    @Bind(R.id.tv_type)
    TextView tvType;

    private PopupWindow popupWindow;

    WheelView wv;

    private RelativeLayout rl_main;
    private TextView tv_start_time,tv_end_time;
    private int curYear;
    private int curMonth;

    @OnClick(R.id.tv_type)
    public void showPop(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupWindowView = inflater.inflate(R.layout.pop_term_select, null);
        popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        initWheelView(popupWindowView);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                makeWindowLight();
            }
        });
        popupWindow.showAtLocation(tvType, Gravity.CENTER | Gravity.BOTTOM, 0, 0);
        makeWindowDark();
    }

    /**
     * 让屏幕变暗
     */
    private void makeWindowDark(){
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.5f;
        window.setAttributes(lp);
    }
    /**
     * 让屏幕变亮
     */
    private void makeWindowLight(){
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 1f;
        window.setAttributes(lp);
    }

    private void initWheelView(View v) {
        types.clear();
        types.add("2680元/年");
        types.add("1000元/季");
        types.add("328元/月");
        wv = (WheelView)v.findViewById(R.id.wheelView);
        wv.setWheelAdapter(new ArrayWheelAdapter(this)); // 文本数据源
        wv.setSkin(WheelView.Skin.Holo); // common皮肤
        wv.setWheelData(types);  // 数据集合
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextColor = getResources().getColor(R.color.red);
        style.selectedTextSize = 20;
        wv.setStyle(style);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        ButterKnife.bind(this);

    }
}
