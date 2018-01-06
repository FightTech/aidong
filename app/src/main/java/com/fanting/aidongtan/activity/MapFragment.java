package com.fanting.aidongtan.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.SupportMapFragment;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.fanting.aidongtan.R;
import com.fanting.aidongtan.model.GymItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by foryoung on 2017/12/27.
 */

public class MapFragment extends Fragment implements BaiduMap.OnMarkerClickListener {

    public MapFragment() {
    }

    MapView mMapView = null;
    BaiduMap mBaiduMap;
    List<LatLng> latLngs;

    //是否第一次定位，如果是第一次定位的话要将自己的位置显示在地图 中间
    private boolean isFirstLocation = true;
    //定位相关
    // 定位相关
    LocationClient mLocClient;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;
    private List<GymItem> gyms = new ArrayList<GymItem>();
    private static final String TAG = GymMapActivity.class.getSimpleName();
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        SDKInitializer.initialize(getActivity().getApplicationContext());
        view = inflater.inflate(R.layout.fragment_gymmap, null);
        mMapView =  (MapView)view.findViewById(R.id.bmapView);
        //初始化地图
        initMap();
        getLatLists();
        setMarker(false);
        initLocation();
        return view;
    }

    private void setMarker(boolean b) {
        setMarker(b, null);
    }


    private void initMap() {
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setCompassEnable(false);
        mBaiduMap.setOnMarkerClickListener(this);
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mMapView. showZoomControls(false);
        UiSettings mUiSettings = mBaiduMap.getUiSettings();
        mUiSettings.setOverlookingGesturesEnabled(false);
        mUiSettings.setRotateGesturesEnabled(false);
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mBaiduMap.hideInfoWindow();
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });



    }

    private void getLatLists() {
        latLngs = new ArrayList<>();
        latLngs.add(new LatLng(39.963175, 116.400244));
        latLngs.add(new LatLng(39.951135, 116.409254));
        latLngs.add(new LatLng(39.925125, 116.404274));
        latLngs.add(new LatLng(39.997115, 116.406204));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        setMarker(true, marker);
        //  gotoDetail();
        showInfoWindow(marker);
        Log.i("marker", marker.toString());
        return false;
    }

    private void showInfoWindow(Marker marker) {
        double latitude, longitude;
        latitude = marker.getPosition().latitude;
        longitude = marker.getPosition().longitude;
        LatLng latLng = new LatLng(latitude,longitude);
        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.window_info_map,mMapView,false);
        mInfoWindow = new InfoWindow(layout, latLng, -95);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoDetail();
            }
        });
        mBaiduMap.hideInfoWindow();
        mBaiduMap.showInfoWindow(mInfoWindow);
    }

    private InfoWindow mInfoWindow;



    private void gotoDetail() {
        startActivity(new Intent(getActivity(), GymAdActivity.class));
    }

    private void setMarker(boolean isClick, Marker marker) {

        mBaiduMap.clear();

        BitmapDescriptor bitmap;

        if (latLngs != null && latLngs.size() != 0) {


            for (LatLng point : latLngs) {
                //构建Marker图标
                if (isClick) {
                    if (marker.getExtraInfo().getString("latlong").equals(point.toString())) {
                        bitmap = BitmapDescriptorFactory
                                .fromResource(R.drawable.healthy_bg);
                    } else {
                        bitmap = BitmapDescriptorFactory
                                .fromResource(R.drawable.healthy_bg_no);
                    }
                } else {
                    bitmap = BitmapDescriptorFactory
                            .fromResource(R.drawable.healthy_bg_no);
                }


                Bundle bundle = new Bundle();
                bundle.putString("latlong", point.toString());
                //构建MarkerOption，用于在地图上添加Marker
                OverlayOptions option = new MarkerOptions()
                        .position(point)
                        .icon(bitmap);
                //在地图上添加Marker，并显示
                Marker myMarker = (Marker) (mBaiduMap.addOverlay(option));
                myMarker.setExtraInfo(bundle);
            }
        }
    }

    private LocationManager locationManager;
    private String provider;

    private void initLocation() {
        mBaiduMap = mMapView.getMap();
        // 设置可改变地图位置
        mBaiduMap.setMyLocationEnabled(true);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        List<String> list = locationManager.getProviders(true);
        if (list.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;

        } else {
            Toast.makeText(getActivity(), "当前不能提供位置信息", Toast.LENGTH_LONG).show();
            return;
        }


        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            Location location = locationManager.getLastKnownLocation(provider);
//            Log.i(TAG, "initLocation: "+location.getLongitude()+" --" + location.getLatitude());
            if (location != null) {
                navigateTo(location);
            }
        }
        locationManager.requestLocationUpdates(provider, 5000, 1,
                locationListener);


    }

    private void navigateTo(Location location) {
        // 按照经纬度确定地图位置
        if (isFirstLocation) {
            LatLng ll = new LatLng(location.getLatitude(),
                    location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            // 移动到某经纬度
            mBaiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(12f);
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
        mBaiduMap.setMyLocationConfiguration(config);


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
            Log.i(TAG, "onLocationChanged: "+arg0);
            // TODO Auto-generated method stub
            // 位置改变则重新定位并显示地图
            navigateTo(arg0);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        //在Fragment执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        //在<span style="font-family: 微软雅黑, 'Microsoft YaHei', sans-serif;">Fragment</span>执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在Fragment执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

}
