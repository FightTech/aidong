package com.fanting.aidongtan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.base.BaseActivity;

/**
 * Created by Administrator on 2017/12/18.
 */

public class RegisterSuccessActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_complete;
    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_register_success);
        tv_complete=findViewById(R.id.tv_complete);
        tv_complete.setOnClickListener(this);

        iv_back=findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_complete:
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
