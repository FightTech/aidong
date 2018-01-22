package com.fanting.aidongtan.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fanting.aidongtan.activity.MyCollectionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/6.
 */

public class MyCollectionPageAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private Context context;
    private MyCollectionFragment myCollectionFragment;
    private List<MyCollectionFragment> fragmentList=new ArrayList<>();


    public MyCollectionPageAdapter(Context context, FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
        this.context = context;
        fragmentList.add( new MyCollectionFragment()) ;
        fragmentList.add( new MyCollectionFragment()) ;
        fragmentList.add( new MyCollectionFragment()) ;
        fragmentList.add( new MyCollectionFragment()) ;

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
