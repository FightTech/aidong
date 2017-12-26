package com.fanting.aidongtan.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.utils.DensityUtil;
import com.fanting.aidongtan.widgets.RecycleViewDivider;
import com.fanting.aidongtan.widgets.SpacesItemDecoration;
import com.lsjwzh.widget.recyclerviewpager.FragmentStatePagerAdapter;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.lsjwzh.widget.recyclerviewpager.TabLayoutSupport;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

/**
 * Created by foryoung on 2017/12/5.
 */

public class RentActivity extends AppCompatActivity {

    protected RecyclerViewPager mRecyclerView;
    private FragmentsAdapter mAdapter;
    private String[] rentType ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        rentType =getResources().getStringArray(R.array.rent_type);
       // setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        initViewPager();
        initTabLayout();
    }



    private void initTabLayout() {
        //给TabLayout增加Tab, 并关联ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        setUpIndicatorWidth(tabLayout,20,20);
        TabLayoutSupport.setupWithViewPager(tabLayout, mRecyclerView, mAdapter);
    }

    protected void initViewPager() {
        mRecyclerView = (RecyclerViewPager) findViewById(R.id.viewpager);
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false);
        mRecyclerView.setLayoutManager(layout);
        mAdapter = new FragmentsAdapter(getSupportFragmentManager());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, 10, getResources().getColor(R.color.gray)));
        mRecyclerView.setLongClickable(true);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(50, mRecyclerView.getAdapter().getItemCount()));
        mRecyclerView.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                Log.d("test", "oldPosition:" + oldPosition + " newPosition:" + newPosition);
            }
        });

    }

    class FragmentsAdapter extends FragmentStatePagerAdapter implements TabLayoutSupport.ViewPagerTabLayoutAdapter {
        LinkedHashMap<Integer, Fragment> mFragmentCache = new LinkedHashMap<>();

        public FragmentsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position, Fragment.SavedState savedState) {
            Fragment f = mFragmentCache.containsKey(position) ? mFragmentCache.get(position)
                    : new RentEquipFragment();
            Bundle b = new Bundle();
            b.putInt("type",position);
            f.setArguments(b);
            Log.e("test", "getItem:" + position + " from cache" + mFragmentCache.containsKey
                    (position));
            if (savedState == null ) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", position);
                f.setArguments(bundle);
                Log.e("test", "setArguments:" + position);
            } else if (!mFragmentCache.containsKey(position)) {
                f.setInitialSavedState(savedState);
                Log.e("test", "setInitialSavedState:" + position);
            }
            mFragmentCache.put(position, f);
            return f;
        }

        @Override
        public void onDestroyItem(int position, Fragment fragment) {
            // onDestroyItem
                Object[] keys = mFragmentCache.keySet().toArray();
                mFragmentCache.remove(keys[0]);
        }

        @Override
        public String getPageTitle(int position) {
            return rentType[position];
        }

        @Override
        public int getItemCount() {
            return rentType.length;
        }
    }

    private void setUpIndicatorWidth(TabLayout tabLayout, int marginLeft, int marginRight) {
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
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    params.setMarginStart(DensityUtil.dip2px(this, marginLeft));
                    params.setMarginEnd(DensityUtil.dip2px(this, marginRight));
                }
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
