package com.fanting.aidongtan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.base.BaseActivity;

/**
 * Created by Administrator on 2017/12/15.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_register;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_login);
        tv_register=findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
        }

    }
}
