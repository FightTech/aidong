package com.fanting.aidongtan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    private Button btn_acitonbar_left,btn_acitonbar_right;
    private TextView tv_actionbar_title;
    private SportWikiAdapter sportWikiAdapter;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_sportwiki);
        rv_wiki=findViewById(R.id.rv_wiki);

        btn_acitonbar_left = findViewById(R.id.btn_acitonbar_left);
        btn_acitonbar_right = findViewById(R.id.btn_acitonbar_right);
        tv_actionbar_title = findViewById(R.id.tv_actionbar_title);
        btn_acitonbar_left.setOnClickListener(this);
        tv_actionbar_title.setText("运动百科");
        btn_acitonbar_right.setBackground(getResources().getDrawable(R.mipmap.search));
        btn_acitonbar_right.setOnClickListener(this);

        rv_wiki.setLayoutManager(new LinearLayoutManager(this));
        sportWikiAdapter=new SportWikiAdapter(this);
        rv_wiki.setAdapter(sportWikiAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_acitonbar_left:
                finish();
                break;
            case R.id.btn_acitonbar_right:
                startActivity(new Intent(this,SearchActivity.class));
                break;
        }
    }
}
