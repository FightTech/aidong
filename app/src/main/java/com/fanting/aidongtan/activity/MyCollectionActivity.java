package com.fanting.aidongtan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.adapter.MyCollectionPageAdapter;
import com.fanting.aidongtan.base.BaseActivity;
import com.fanting.aidongtan.widgets.PagerSlidingTabStrip;

/**
 * Created by Administrator on 2018/1/20.
 */

public class MyCollectionActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_acitonbar_left, btn_acitonbar_right;
    private TextView tv_actionbar_title;

    private MyCollectionPageAdapter recommendPageAdapter;
    private ViewPager vp_mycollection;
    private PagerSlidingTabStrip tabs;
    private String[] titles={"课程","教练","运动百科","爱看"};

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_mycollection);
        btn_acitonbar_left = findViewById(R.id.btn_acitonbar_left);
        tv_actionbar_title = findViewById(R.id.tv_actionbar_title);
        btn_acitonbar_left.setOnClickListener(this);
        tv_actionbar_title.setText("我的收藏");

        vp_mycollection=findViewById(R.id.vp_mycollection);
        tabs=findViewById(R.id.tabs);
        recommendPageAdapter = new MyCollectionPageAdapter(this, getSupportFragmentManager(), titles);
        vp_mycollection.setAdapter(recommendPageAdapter);
        tabs.setViewPager(vp_mycollection);
        tabs.setSelectedTextColor(getResources().getColor(R.color.selectedTabTextColor));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_acitonbar_left:
                finish();
                break;
        }
    }
}
