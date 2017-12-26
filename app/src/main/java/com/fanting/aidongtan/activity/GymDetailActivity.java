package com.fanting.aidongtan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.adapter.CommitListAdapter;
import com.fanting.aidongtan.adapter.EquipListAdapter;
import com.fanting.aidongtan.adapter.RecycleAbstractAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by foryoung on 2017/12/23.
 */

public class GymDetailActivity extends Activity {


    @Bind(R.id.equip_list)
    RecyclerView equipRecyclerView;

    @Bind(R.id.commit_list)
    RecyclerView commitRecyclerView;

    private RecycleAbstractAdapter equipAdapter , commitAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_detail);
        ButterKnife.bind(this);
        initEquipRecyclerView();
        initCommitRecyclerView();
    }

    private void initCommitRecyclerView() {
        //RecyclerView三部曲+LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        commitRecyclerView.setLayoutManager(linearLayoutManager);
      //  initData();
        commitAdapter = new CommitListAdapter(mList);
        commitRecyclerView.setAdapter(commitAdapter);
        setFooterView(commitRecyclerView,R.layout.footer,commitAdapter );
    }

    private void initEquipRecyclerView() {
        //RecyclerView三部曲+LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        equipRecyclerView.setLayoutManager(linearLayoutManager);
        initData();
        equipAdapter = new EquipListAdapter(mList);
        equipRecyclerView.setAdapter(equipAdapter);
        setFooterView(equipRecyclerView,R.layout.footer,equipAdapter);
    }

    //初始化RecyclerView中每个item的数据
    private void initData(){
        mList = new ArrayList<String>();
        for (int i = 0; i < 2; i++){
            mList.add("item" + i);
        }
    }

//    private void setHeaderView(RecyclerView view){
//        View header = LayoutInflater.from(this).inflate(R.layout.header, view, false);
//        adapter.setHeaderView(header);
//    }

    private void setFooterView(RecyclerView view,int layout,RecycleAbstractAdapter adapter ){
        View footer = LayoutInflater.from(this).inflate(layout, view, false);
        adapter.setFooterView(footer);
    }
}

