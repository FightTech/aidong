package com.fanting.aidongtan.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.base.BaseActivity;

/**
 * Created by Administrator on 2017/12/21.
 */

public class SportRecordActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_acitonbar_left, btn_acitonbar_right;
    private TextView tv_actionbar_title;
    private LinearLayout rl_actionbar;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_sport_record);
        btn_acitonbar_left = findViewById(R.id.btn_acitonbar_left);
        btn_acitonbar_right = findViewById(R.id.btn_acitonbar_right);
        tv_actionbar_title = findViewById(R.id.tv_actionbar_title);
        btn_acitonbar_left.setOnClickListener(this);
        tv_actionbar_title.setText("骑行数据");
        rl_actionbar=findViewById(R.id.rl_actionbar);
        rl_actionbar.setBackgroundColor(Color.BLACK);


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
