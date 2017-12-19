package com.fanting.aidongtan.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.fanting.aidongtan.R;
import com.fanting.aidongtan.model.GymItem;
import com.fanting.aidongtan.widgets.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by foryoung on 2017/11/29.
 */

public class GymMapActivity extends Activity {
    private ViewPager vp;
    MapView mMapView = null;
    private List<GymItem> gyms = new ArrayList<GymItem>();
    private static final String TAG = GymMapActivity.class.getSimpleName();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_gymmap);
        mMapView = (MapView) findViewById(R.id.bmapView);
        vp = (ViewPager) findViewById(R.id.vp);
        //设置ViewPager的适配器
        gyms.add(new GymItem());
        gyms.add(new GymItem());
        gyms.add(new GymItem());
        final MyViewPagerAdapter adapter = new MyViewPagerAdapter(gyms, this);
        vp.setPageTransformer(true, new DepthPageTransformer());

        vp.setAdapter(adapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }


    public class MyViewPagerAdapter extends PagerAdapter {
        private List<GymItem> items;
        private Context context;

        //有参构造
        public MyViewPagerAdapter(List<GymItem> items, Context context) {
            super();
            this.items = items;
            this.context = context;
        }

        //获得长度
        @Override
        public int getCount() {

            return items.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }

        //展示的view
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //获得展示的view

            ViewGroup view = (ViewGroup) View.inflate(getApplicationContext(), R.layout.layout_flip_map, null);
            ImageView image_logo = (ImageView) view.findViewById(R.id.image_logo);
            image_logo.setImageBitmap(items.get(position).getLogo());

            TextView tvName = (TextView) view.findViewById(R.id.tv_name);
            TextView tvDistance = (TextView) view.findViewById(R.id.tv_distance);
            TextView tvSeat = (TextView) view.findViewById(R.id.tv_seat);

            //添加到容器
            container.addView(view);
            //返回显示的view
            return view;
        }

        //销毁view
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //从容器中移除view
            container.removeView((View) object);
        }

        private View mCurrentView;

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            mCurrentView = (View) object;
        }

        public View getPrimaryItem() {
            return mCurrentView;
        }
    }
}
