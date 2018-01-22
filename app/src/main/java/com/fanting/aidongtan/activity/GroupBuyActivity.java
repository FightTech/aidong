package com.fanting.aidongtan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.adapter.GroupBuyAdapter;
import com.fanting.aidongtan.base.BaseActivity;

/**
 * Created by Administrator on 2018/1/20.
 */

public class GroupBuyActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView rv_group_buy;
    private Button btn_acitonbar_left, btn_acitonbar_right;
    private TextView tv_actionbar_title;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_group_buy);

        btn_acitonbar_left = findViewById(R.id.btn_acitonbar_left);
        btn_acitonbar_right = findViewById(R.id.btn_acitonbar_right);
        tv_actionbar_title = findViewById(R.id.tv_actionbar_title);
        btn_acitonbar_left.setOnClickListener(this);
        tv_actionbar_title.setText("我的团购");

        rv_group_buy=findViewById(R.id.rv_group_buy);
        rv_group_buy.setLayoutManager(new LinearLayoutManager(this));
        rv_group_buy.setAdapter(new GroupBuyAdapter(this));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_acitonbar_left:
                finish();
                break;
        }
    }
}
