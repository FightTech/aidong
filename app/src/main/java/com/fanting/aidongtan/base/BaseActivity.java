package com.fanting.aidongtan.base;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

import com.fanting.aidongtan.AppContext;

/**
 * 
 * @Description:基础基Activity
 * @author:zhuzuofei
 * @date:2014-7-23 上午11:17:37
 */
public class BaseActivity extends FragmentActivity implements Init {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	//	overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//		AppContext.appManager.addActivity(this);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValue() {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}
}
