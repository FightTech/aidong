package com.fanting.aidongtan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.adapter.SportWikiAdapter;
import com.fanting.aidongtan.base.BaseActivity;

/**
 * Created by Administrator on 2018/1/7.
 */

public class SportWikiActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView rv_wiki;
    private ImageView iv_acitonbar_left, iv_acitonbar_right;
    private TextView tv_actionbar_title;
    private SportWikiAdapter sportWikiAdapter;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_sportwiki);
        rv_wiki=findViewById(R.id.rv_wiki);

        iv_acitonbar_left = findViewById(R.id.iv_acitonbar_left);
        iv_acitonbar_right = findViewById(R.id.iv_acitonbar_right);
        tv_actionbar_title = findViewById(R.id.tv_actionbar_title);
        iv_acitonbar_left.setOnClickListener(this);
        tv_actionbar_title.setText("运动百科");
        iv_acitonbar_right.setBackground(getResources().getDrawable(R.mipmap.search));
        iv_acitonbar_right.setOnClickListener(this);

        rv_wiki.setLayoutManager(new LinearLayoutManager(this));
        sportWikiAdapter=new SportWikiAdapter(this);
        rv_wiki.setAdapter(sportWikiAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_acitonbar_left:
                finish();
                break;
            case R.id.iv_acitonbar_right:
                startActivity(new Intent(this,SearchActivity.class));
                break;
        }
    }
}
