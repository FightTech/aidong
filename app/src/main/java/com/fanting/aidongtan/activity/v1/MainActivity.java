package com.fanting.aidongtan.activity.v1;

import com.fanting.aidongtan.activity.LookFragment;
import com.fanting.aidongtan.activity.MeFragment;
import com.fanting.aidongtan.activity.PlayFragment;
import com.fanting.aidongtan.base.BaseActivity;;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.fanting.aidongtan.AppContext;
import com.fanting.aidongtan.R;
import com.fanting.aidongtan.utils.AppManager;
import com.fanting.aidongtan.widgets.StatusBarCompat;

public class MainActivity extends BaseActivity implements OnClickListener{

    private ImageView btnSport, btnLook,btnMe;
    private FragmentManager mFragmentManager;

    private boolean isHomePage;

    private long firstime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v1);
        AppManager.getAppManager().addActivity(this);
        initView();
        mFragmentManager = getSupportFragmentManager();
        //开始时显示主页
        setTabSelection(0);
  //      StatusBarCompat.compat(this);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//        }

//        setFitsSystemWindows(this, true);
    }


    public void initView() {
        btnSport = (ImageView) findViewById(R.id.im_sport);
        btnLook = (ImageView) findViewById(R.id.im_look);
        btnMe = (ImageView) findViewById(R.id.im_me);
        btnSport.setOnClickListener(this);
        btnLook.setOnClickListener(this);
        btnMe.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_sport:
                setTabSelection(0);
                isHomePage = true;
                break;


            case R.id.im_look:
                setTabSelection(1);
                isHomePage = false;
                break;

            case R.id.im_me:
                setTabSelection(2);
                isHomePage = false;
                break;
        }
    }

    private FragmentGroup sportFragment;
    private LookFragment lookFragment;
    private MeFragment meFragment;


    /**
     * 设置页面最外层布局 FitsSystemWindows 属性
     * @param activity
     * @param value
     */
    public static void setFitsSystemWindows(Activity activity, boolean value) {
        ViewGroup contentFrameLayout = (ViewGroup) activity.findViewById(android.R.id.content);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(value);
        }
    }




    private void setTabSelection(int index){
        //开启一个fragment事务
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        //隐藏所有的fragment
        hideFragments(transaction);

        switch (index) {
            case 0:
                if (sportFragment == null) {
                    //创建页面并添加
                    sportFragment = new FragmentGroup();
                    transaction.add(R.id.content, sportFragment, "home");
                } else {
                    transaction.show(sportFragment);
                }

                btnSport.setSelected(true);
                btnLook.setSelected(false);
                btnMe.setSelected(false);
                break;


            case 1:
                if (lookFragment == null) {
                    lookFragment = new LookFragment();
                    transaction.add(R.id.content, lookFragment, "search");
                } else {
                    transaction.show(lookFragment);
                }

                btnSport.setSelected(false);
                btnLook.setSelected(true);
                btnMe.setSelected(false);


                break;
            case 2:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    transaction.add(R.id.content, meFragment, "search");
                } else {
                    transaction.show(meFragment);
                }
                btnSport.setSelected(false);
                btnLook.setSelected(false);
                btnMe.setSelected(true);


                break;


        }
        transaction.commit();
    }

    /**将所有fragment设置为隐藏状态
     * @param transaction 用于对fragment执行操作的事务*/
    private void hideFragments(FragmentTransaction transaction) {
        if (sportFragment != null) {
            transaction.hide(sportFragment);
        }
        if (lookFragment != null) {
            transaction.hide(lookFragment);
        }

        if (meFragment != null) {
            transaction.hide(meFragment);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(isHomePage){
            if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
                long secondtime = System.currentTimeMillis();
                if (secondtime - firstime > 3000) {
                    Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstime = System.currentTimeMillis();
                    return true;
                } else {
                    AppContext.exit();
                }
            }
            return super.onKeyDown(keyCode, event);
        }
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            goBack();
        }
        return isHomePage;

    }

    public void goBack(){
        if (!isHomePage) {
            //	setTabSelection(0);
            isHomePage = true;
        }
    }
}
