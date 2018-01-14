package com.fanting.aidongtan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.adapter.HealthDataAdapter;
import com.fanting.aidongtan.base.BaseActivity;
import com.fanting.aidongtan.listener.RecycleViewItemClickListener;

/**
 * Created by Administrator on 2017/12/20.
 */

public class HealthDataActivity extends BaseActivity implements View.OnClickListener, RecycleViewItemClickListener {
    private RecyclerView rv_healthdata;
    private Button btn_acitonbar_left, btn_acitonbar_right;
    private TextView tv_actionbar_title;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_healthdata);

        btn_acitonbar_left = findViewById(R.id.btn_acitonbar_left);
        btn_acitonbar_right = findViewById(R.id.btn_acitonbar_right);
        tv_actionbar_title = findViewById(R.id.tv_actionbar_title);
        btn_acitonbar_left.setOnClickListener(this);
        tv_actionbar_title.setText("健康数据");

        rv_healthdata = findViewById(R.id.rv_healthdata);
        rv_healthdata.setLayoutManager(new LinearLayoutManager(this));
        HealthDataAdapter healthDataAdapter = new HealthDataAdapter(this);
        rv_healthdata.setAdapter(healthDataAdapter);
        healthDataAdapter.setItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_acitonbar_left:
                finish();
                break;
        }
    }

    @Override
    public void onItemClickListene(int position) {
        startActivity(new Intent(this,SportRecordActivity.class));
    }
}
