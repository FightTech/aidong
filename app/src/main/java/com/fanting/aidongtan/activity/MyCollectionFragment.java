package com.fanting.aidongtan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanting.aidongtan.R;

/**
 * Created by Administrator on 2018/1/20.
 */

public class MyCollectionFragment extends Fragment {
    private int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return   inflater.inflate(R.layout.fragment_mycollection,container,false);
    }

    public void setPosition(int position){
        this.position=position;
    }
}
