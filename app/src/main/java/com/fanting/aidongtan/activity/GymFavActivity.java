package com.fanting.aidongtan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.adapter.GymListAdapter;
import com.fanting.aidongtan.adapter.RecycleAbstractAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by foryoung on 2017/12/28.
 */

public class GymFavActivity extends Activity
{

    @Bind(R.id.recycler_view)
    RecyclerView rv ;
    private List<String> mList;
    private RecycleAbstractAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_fav);
        ButterKnife.bind(this);
        initEquipRecyclerView();
    }


    private void initEquipRecyclerView() {
        //RecyclerView三部曲+LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        initData();
        adapter = new GymListAdapter(mList);
        rv.setAdapter(adapter);
    }

    //初始化RecyclerView中每个item的数据
    private void initData(){
        mList = new ArrayList<String>();
        for (int i = 0; i < 6; i++){
            mList.add("item" + i);
        }
    }



}
