package com.fanting.aidongtan.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.fanting.aidongtan.R;
import com.fanting.aidongtan.model.GymItem;
import com.fanting.aidongtan.widgets.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by foryoung on 2017/11/29.
 */

public class GymMapActivity extends Activity implements BaiduMap.OnMarkerClickListener {
    private ViewPager vp;
    MapView mMapView = null;
    BaiduMap mBaiduMap;


    //是否第一次定位，如果是第一次定位的话要将自己的位置显示在地图 中间
    private boolean isFirstLocation = true;
    //定位相关
    // 定位相关
    LocationClient mLocClient;
    private LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;
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


        //初始化地图
           initMap();
        //定位

        initLocation();


    }

    private void initMap() {
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setCompassEnable(false);
        mBaiduMap.setOnMarkerClickListener(this);
    }

    private LocationManager locationManager;
    private String provider;

    private void initLocation() {
        mBaiduMap = mMapView.getMap();
        // 设置可改变地图位置
        mBaiduMap.setMyLocationEnabled(true);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> list = locationManager.getProviders(true);
        if (list.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;

        } else {
            Toast.makeText(this, "当前不能提供位置信息", Toast.LENGTH_LONG).show();
            return;
        }


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                navigateTo(location);
            }
        }
        locationManager.requestLocationUpdates(provider, 5000, 1,
                locationListener);

        LatLng point = new LatLng(39.963175, 116.400244);

        //构建Marker图标

        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.store_marker);

        //构建MarkerOption，用于在地图上添加Marker

        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);

        //在地图上添加Marker，并显示

        mBaiduMap.addOverlay(option);
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

    @Override
    public boolean onMarkerClick(Marker marker) {
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.store_marker_selected);
        marker.setIcon(bitmap);
        Log.i("marker",marker.toString());
        return false;
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


    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderDisabled(String arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onLocationChanged(Location arg0) {
            // TODO Auto-generated method stub
            // 位置改变则重新定位并显示地图
            navigateTo(arg0);
        }
    };

    private void navigateTo(Location location) {
        // 按照经纬度确定地图位置
        if (isFirstLocation) {
            LatLng ll = new LatLng(location.getLatitude(),
                    location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            // 移动到某经纬度
            mBaiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomBy(4f);
            // 放大
            mBaiduMap.animateMapStatus(update);

            isFirstLocation = false;
        }

        MyLocationData locData = new MyLocationData.Builder().latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();

// 设置定位数据
        mBaiduMap.setMyLocationData(locData);

// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.my_marker);
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);




        // 显示个人位置图标
//        MyLocationData.Builder builder = new MyLocationData.Builder();
//        builder.latitude(location.getLatitude());
//        builder.longitude(location.getLongitude());3
//        MyLocationData data = builder.build();
        mBaiduMap.setMyLocationConfiguration(config);
       // mBaiduMap.setMyLocationData(data);


    }


}
