package com.fanting.aidongtan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.fanting.aidongtan.R;

import butterknife.ButterKnife;

/**
 * Created by foryoung on 2018/1/6.
 */

public class PromotionActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);
    }
}
