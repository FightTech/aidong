package com.fanting.aidongtan.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.adapter.ReserveCoachAdapter;
import com.fanting.aidongtan.adapter.TrainingAdapter;
import com.fanting.aidongtan.base.BaseActivity;
import com.fanting.aidongtan.listener.RecycleViewItemClickListener;
import com.fanting.aidongtan.utils.DisplayUtil;
import com.fanting.aidongtan.widgets.SelectTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/30.
 */

public class ReserveCoachActivity extends BaseActivity implements View.OnClickListener, RecycleViewItemClickListener {
    @Bind(R.id.rv_coach)
    RecyclerView rv_coach;
    @Bind(R.id.iv_acitonbar_left)
    ImageView iv_acitonbar_left;
    @Bind(R.id.iv_acitonbar_right)
    ImageView iv_acitonbar_right;
    @Bind(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;
    @Bind(R.id.tv_filter)
    TextView tv_filter;
    @Bind(R.id.tv_sort)
    TextView tv_sort;
    @Bind(R.id.tv_price)
    TextView tv_price;
    private ReserveCoachAdapter reserveCoachAdapter;
    private PopupWindow filterPopupWindow,sortPopupWindow,pricePopupWindow;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_reservecoach);
        ButterKnife.bind(this);
        iv_acitonbar_left.setOnClickListener(this);
        tv_actionbar_title.setText("私教预约");

        rv_coach.setLayoutManager(new LinearLayoutManager(this));
        reserveCoachAdapter=new ReserveCoachAdapter(this);
        reserveCoachAdapter.setOnItemClickListen(this);
        rv_coach.setAdapter(reserveCoachAdapter);
        tv_filter.setOnClickListener(this);
        tv_sort.setOnClickListener(this);
        tv_price.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_acitonbar_left:
                finish();
                break;
            case R.id.tv_filter:
                showFilterPopWindow();
                break;
            case R.id.tv_sort:
               showSortPopWindow();
                break;
            case R.id.tv_price:
               showPricePopWindow();
                break;

        }
    }

    //显示价格
    private void showPricePopWindow() {
        if(pricePopupWindow==null){
            pricePopupWindow=new PopupWindow(this);
            pricePopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            pricePopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            View priceLayout= LayoutInflater.from(this).inflate(R.layout.layout_price,null);
            pricePopupWindow.setContentView(priceLayout);
            pricePopupWindow.setBackgroundDrawable(new BitmapDrawable());
            pricePopupWindow.setFocusable(true);
            pricePopupWindow.setOutsideTouchable(true);
        }
        pricePopupWindow.showAtLocation( getWindow().getDecorView(), Gravity.TOP,0, DisplayUtil.dip2px(this,130));
    }

    //显示排序
    private void showSortPopWindow() {
        if(sortPopupWindow==null){
            sortPopupWindow=new PopupWindow(this);
            sortPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            sortPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            View sortLayout= LayoutInflater.from(this).inflate(R.layout.layout_sort,null);
            final RadioButton rb_sentiment=sortLayout.findViewById(R.id.rb_sentiment);
            final RadioButton rb_distance=sortLayout.findViewById(R.id.rb_distance);
            final RadioButton rb_price=sortLayout.findViewById(R.id.rb_price);
            rb_sentiment.setTextColor(Color.WHITE);
            RadioGroup rg_sort=sortLayout.findViewById(R.id.rg_sort);
            rg_sort.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (i){
                        case R.id.rb_sentiment:
                            rb_sentiment.setTextColor(Color.WHITE);
                            rb_distance.setTextColor(getResources().getColor(R.color.black));
                            rb_price.setTextColor(getResources().getColor(R.color.black));
                            break;
                        case R.id.rb_distance:
                            rb_distance.setTextColor(Color.WHITE);
                            rb_sentiment.setTextColor(getResources().getColor(R.color.black));
                            rb_price.setTextColor(getResources().getColor(R.color.black));
                            break;
                        case R.id.rb_price:
                            rb_price.setTextColor(Color.WHITE);
                            rb_sentiment.setTextColor(getResources().getColor(R.color.black));
                            rb_distance.setTextColor(getResources().getColor(R.color.black));
                            break;
                    }
                }
            });
            sortPopupWindow.setContentView(sortLayout);
            sortPopupWindow.setBackgroundDrawable(new BitmapDrawable());
            sortPopupWindow.setFocusable(true);
            sortPopupWindow.setOutsideTouchable(true);
        }
        sortPopupWindow.showAtLocation( getWindow().getDecorView(), Gravity.TOP,0, DisplayUtil.dip2px(this,130));
        }



    private List<String> datas=new ArrayList<>();

    //显示筛选
    private void showFilterPopWindow() {
        if(filterPopupWindow==null){
            filterPopupWindow=new PopupWindow(this);
            filterPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            filterPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            View filterLayout= LayoutInflater.from(this).inflate(R.layout.layout_filter,null);
            final TextView tv_man=filterLayout.findViewById(R.id.tv_man);
            final TextView tv_woman=filterLayout.findViewById(R.id.tv_woman);
            tv_man.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(tv_man.isSelected()){
                        tv_man.setTextColor(getResources().getColor(R.color.gray_8f));
                        tv_man.setSelected(false);
                    }else{
                        tv_man.setTextColor(Color.WHITE);
                        tv_man.setSelected(true);
                        tv_woman.setTextColor(getResources().getColor(R.color.gray_8f));
                        tv_woman.setSelected(false);
                    }
                }
            });
            tv_woman.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(tv_woman.isSelected()){
                        tv_woman.setTextColor(getResources().getColor(R.color.gray_8f));
                        tv_woman.setSelected(false);
                    }else{
                        tv_woman.setTextColor(Color.WHITE);
                        tv_woman.setSelected(true);
                        tv_man.setTextColor(getResources().getColor(R.color.gray_8f));
                        tv_man.setSelected(false);
                    }
                }
            });
            RecyclerView rv_training=filterLayout.findViewById(R.id.rv_training);
            rv_training.setLayoutManager(new GridLayoutManager(this,4));
            datas.add("增肌");
            datas.add("产后恢复");
            datas.add("格斗");
            datas.add("普拉提");
            datas.add("拉伸");
            datas.add("跆拳道");
            datas.add("太极");
            datas.add("调理");
            datas.add("瑜伽");
            datas.add("冬阴功");
            datas.add("康复");
            datas.add("游泳");
            final TrainingAdapter trainingAdapter = new TrainingAdapter(this, datas);
            rv_training.setAdapter(trainingAdapter);

            filterLayout.findViewById(R.id.tv_reset).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("djj","reset");
                    if(trainingAdapter!=null){
                        trainingAdapter.reset();
                        tv_man.setTextColor(getResources().getColor(R.color.gray_8f));
                        tv_man.setSelected(false);
                        tv_woman.setTextColor(getResources().getColor(R.color.gray_8f));
                        tv_woman.setSelected(false);
                    }
                }
            });
            filterLayout.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("djj","confirm");
                    filterPopupWindow.dismiss();
                }
            });
            filterPopupWindow.setContentView(filterLayout);
            filterPopupWindow.setBackgroundDrawable(new BitmapDrawable());
            filterPopupWindow.setFocusable(true);
            filterPopupWindow.setOutsideTouchable(true);
        }
        filterPopupWindow.showAtLocation( getWindow().getDecorView(), Gravity.TOP,0, DisplayUtil.dip2px(this,130));
    }


    @Override
    public void onItemClickListene(int position) {
        startActivity(new Intent(this,CoachDetialActivity.class));
    }
}
