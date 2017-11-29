package com.fanting.aidongtan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.widgets.BannerBaseAdapter;
import com.oragee.banners.BannerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SportFragment extends Fragment implements OnClickListener {

    private List<View> viewList;
    private int[] imgs = {R.drawable.sport_selected,R.drawable.sport_selected,R.drawable.sport_selected,R.drawable.sport_selected};
    private List<BannerBean> datas = new ArrayList<>();
    private BannerAdapter mAdapter;

    @Bind(R.id.ad_vip)
    ImageView imFind;


    public SportFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport, null);
        ButterKnife.bind(this,view);
        initView(view);
        initBanner(view);
        initData();
        return view;
    }

    private void initView(View v) {

        viewList = new ArrayList<View>();
        for (int i = 0; i < imgs.length; i++) {
            ImageView image = new ImageView(getActivity());
            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //设置显示格式
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setImageResource(imgs[i]);
            viewList.add(image);
        }


        BannerView bannerView = (BannerView) v.findViewById(R.id.banner);
        bannerView.setViewList(viewList);
        bannerView.startLoop(true);
    }

    private void addListener() {

    }

    @OnClick(R.id.ad_vip)
    public void findGym(View view) {
        startActivity(new Intent(getActivity(),GymMapActivity.class));
    }

    @Override
    public void onClick(View v) {

    }

    private void intoFunction(Map<String, String> linkMap) {
        Intent intent = null;
        try {
            intent = new Intent();
            intent.setClass(getActivity(), Class.forName("com.asiainfo.activity." + linkMap.get("link")));
            if (linkMap.containsKey("remark")) {
                String[] extras = linkMap.get("remark").split("=");
                //System.out.println();
                intent.putExtra(extras[0], extras[1]);
            }
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private  com.fanting.aidongtan.widgets.BannerView   bannerView;

    private void initBanner(View view) {
        bannerView = (com.fanting.aidongtan.widgets.BannerView) view.findViewById(R.id.bannerView);
        bannerView.setAdapter(mAdapter = new BannerAdapter(getActivity()));
        mAdapter.setOnPageTouchListener(new BannerBaseAdapter.OnPageTouchListener<BannerBean>() {
            @Override
            public void onPageClick(int position, BannerBean bannerBean) {
                // 页面点击
                //Toast.makeText(BannerActivity.this, bannerBean.title, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageDown() {
                // 按下，可以停止轮播
                //   Toast.makeText(BannerActivity.this, "按下", Toast.LENGTH_SHORT).show();
                //bannerView.stopAutoScroll();
            }

            @Override
            public void onPageUp() {
                // 抬起开始轮播
                //  Toast.makeText(BannerActivity.this, "抬起", Toast.LENGTH_SHORT).show();
                //bannerView.startAutoScroll();
            }
        });
    }

    private void initData() {
        datas.clear();

        datas.add(new BannerBean(R.drawable.statics, "7 Km"));
        datas.add(new BannerBean(R.drawable.statics, "8 Km"));
        datas.add(new BannerBean(R.drawable.statics, "9 Km"));
        datas.add(new BannerBean(R.drawable.statics, "10 Km"));
        datas.add(new BannerBean(R.drawable.statics, "11 Km"));
        // 数据更新之后需要设置
        mAdapter.setData(datas);
        bannerView.stopAutoScroll();
    }


    private class BannerAdapter extends BannerBaseAdapter<BannerBean> {

        public BannerAdapter(Context context) {
            super(context);
        }

        @Override
        protected int getLayoutResID() {
            return R.layout.item_banner;
        }

        @Override
        protected void convert(View convertView, BannerBean data) {
            setImage(R.id.pageImage, data.imageRes);
            setText(R.id.pageText, data.title);
        }
    }


    private class BannerBean implements Serializable {
        public String imageUrl;
        public String title;
        public @IntegerRes
        int imageRes;

        public BannerBean(int imageRes, String title) {
            this.imageRes = imageRes;
            this.title = title;
        }
        // 图片宽高传过来时一般是统一的，所以这里不处理
    }


}