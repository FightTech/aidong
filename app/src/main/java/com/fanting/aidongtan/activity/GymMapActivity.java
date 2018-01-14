package com.fanting.aidongtan.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by foryoung on 2017/11/29.
 */

public class GymMapActivity extends FragmentActivity {

    private Fragment mapFragment, listFragment;
    TextView tv;
    @Bind(R.id.image_list)
    CheckBox cv;
    @Bind(R.id.rg)
    RadioGroup rg;

    @Bind(R.id.type_rb1)
    RadioButton rb1;
    @Bind(R.id.type_rb2)
    RadioButton rb2;
    @Bind(R.id.type_rb3)
    RadioButton rb3;

    private PopupWindow popupWindow;


    @OnCheckedChanged({R.id.type_rb1,R.id.type_rb2,R.id.type_rb3})
    public void toActivity(RadioButton rb){

        if(rb.isChecked()){
            switch (rb.getId()){
                case R.id.type_rb1:
                    startActivity(new Intent(GymMapActivity.this,VipCenterActivity.class));
                    break;
                case R.id.type_rb2:
                    showPop();
                    break;
                case R.id.type_rb3:
                    startActivity(new Intent(GymMapActivity.this,GymFavActivity.class));
                    break;
            }
        }



    }

    private void showPop() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupWindowView = inflater.inflate(R.layout.pop_friend_select, null);
        popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        ///  initWheelView(popupWindowView);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                makeWindowLight();
            }
        });
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        makeWindowDark();
    }

    /**
     * 让屏幕变暗
     */
    private void makeWindowDark(){
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.2f;
        window.setAttributes(lp);



        WindowManager wm= (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics= new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        popupWindow.setHeight(metrics.heightPixels);
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


    @OnClick(R.id.image_list)
    public void exchange(CheckBox view) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (!view.isChecked()){
            hideFragments(transaction,listFragment);
            showFragment(true);
        }else{
            hideFragments(transaction,mapFragment);
            showFragment(false);
        }
    }

    private void hideFragments(FragmentTransaction transaction, Fragment fragment) {
        if (fragment != null) {
            transaction.hide(fragment);
        }
        transaction.commit();
    }

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymmap);
        ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();
        initView();
    }

    private void initView() {

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

            }
        });
        showFragment(true);
    }

    private void showFragment(Boolean b){
        //开启一个fragment事务
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        //隐藏所有的fragment
        if(b){
            if (mapFragment == null) {
                mapFragment = new MapFragment();
                transaction.add(R.id.content, mapFragment, "handle");
            } else {
                transaction.show(mapFragment);
            }
        }else{
            if (listFragment == null) {
                listFragment = new GymListFragment();
                transaction.add(R.id.content, listFragment, "handle");
            } else {
                transaction.show(listFragment);
            }
        }


        transaction.commit();
    }


}
