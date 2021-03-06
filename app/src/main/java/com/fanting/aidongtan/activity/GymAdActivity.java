package com.fanting.aidongtan.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.adapter.EquipListAdapter;
import com.fanting.aidongtan.adapter.PromotionAdapter;
import com.fanting.aidongtan.adapter.RecycleAbstractAdapter;
import com.fanting.aidongtan.widgets.BannerBaseAdapter;
import com.oragee.banners.BannerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by foryoung on 2017/12/23.
 */

public class GymAdActivity extends Activity {
    private  com.fanting.aidongtan.widgets.BannerView   bannerView;
    private int[] imgs = {R.drawable.sport_selected,R.drawable.sport_selected,R.drawable.sport_selected,R.drawable.sport_selected};
    private List<View> viewList;
    private List<String> mList;
    private RecycleAbstractAdapter promotionAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.im_detail)
    ImageButton title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_ad);
        ButterKnife.bind(this);
        initData();
        initView();
        initRecyclerView();
    }

    private void initRecyclerView() {
        //RecyclerView三部曲+LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        initData();
        promotionAdapter = new PromotionAdapter(mList);
        promotionAdapter.setOnItemClickListener(new RecycleAbstractAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()){
                    case R.id.bt_buynow:
                        startActivity(new Intent(GymAdActivity.this,PromotionActivity.class));
                        break;

                    case R.id.bt_detail:
                        startActivity(new Intent(GymAdActivity.this,PromotionActivity.class));
                        break;
                }
            }
        });
        recyclerView.setAdapter(promotionAdapter);
    }

    //初始化RecyclerView中每个item的数据
    private void initData(){
        mList = new ArrayList<String>();
        for (int i = 0; i < 5; i++){
            mList.add("item" + i);
        }
    }


    @OnClick(R.id.im_detail)
    public void toDetail(View view) {
        startActivity(new Intent(GymAdActivity.this,GymDetailActivity.class));
    }

    private void initView() {
        viewList = new ArrayList<View>();
        for (int i = 0; i < imgs.length; i++) {
            ImageView image = new ImageView(this);
            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //设置显示格式
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setImageResource(imgs[i]);
            viewList.add(image);
        }
        BannerView bannerView = (BannerView) findViewById(R.id.banner);
        bannerView.setViewList(viewList);
        bannerView.startLoop(true);





    }
}
