package com.fanting.aidongtan.activity.v1;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.activity.MapFragment;
import com.fanting.aidongtan.activity.RentActivity;
import com.fanting.aidongtan.utils.DensityUtil;
import com.lsjwzh.widget.recyclerviewpager.TabLayoutSupport;


public class FragmentGroup extends Fragment {

    protected ViewPager mViewPager;
    private FragmentGroup.FragmentsAdapter mAdapter;
    private View v;
    private String[] fragments = {"\b\b\b健身房\b\b\b", "\b\b\b运动\b\b\b"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_group, container, false);

        initViewPager();
        initTabLayout();
        return v;
    }

    private void initTabLayout() {
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        setUpIndicatorWidth(tabLayout);
    }

    private void initViewPager() {
        mViewPager = (ViewPager) v.findViewById(R.id.viewpager);
        mAdapter = new FragmentGroup.FragmentsAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setNestedScrollingEnabled(false);
    }

    private void setUpIndicatorWidth(TabLayout tabLayout) {
        Class<?> tabLayoutClass = tabLayout.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayoutClass.getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        LinearLayout layout = null;
        try {
            if (tabStrip != null) {
                layout = (LinearLayout) tabStrip.get(tabLayout);
            }
            for (int i = 0; i < layout.getChildCount(); i++) {
                View child = layout.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                params.gravity = Gravity.CENTER;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    params.setMarginStart(DensityUtil.dip2px(getActivity(), 20f));
                    params.setMarginEnd(DensityUtil.dip2px(getActivity(), 20f));
                }
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    class FragmentsAdapter extends FragmentPagerAdapter implements TabLayoutSupport.ViewPagerTabLayoutAdapter {
        private static final String TAG ="FragmentsAdapter" ;
        LinkedHashMap<Integer, Fragment> mFragmentCache = new LinkedHashMap<>();

        public FragmentsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = mFragmentCache.containsKey(position) ? mFragmentCache.get(position)
                    : (position == 0 ? new MapFragment() : new SportFragment());
            mFragmentCache.put(position, f);
            Log.i(TAG, "getItem: "+ "positon= " + position+ "fragmetn= "+ f);
            return f;
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Object[] keys = mFragmentCache.keySet().toArray();
            mFragmentCache.remove(keys[0]);
        }

        @Override
        public String getPageTitle(int position) {
            return fragments[position];
        }

        @Override
        public int getItemCount() {
            return fragments.length;
        }
    }



}
