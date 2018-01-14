package com.fanting.aidongtan.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.base.BaseActivity;

/**
 * Created by Administrator on 2018/1/4.
 */

public class CoachDetialActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_acitonbar_left, btn_acitonbar_right;
    private TextView tv_actionbar_title;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_coachdetial);

        btn_acitonbar_left = findViewById(R.id.btn_acitonbar_left);
        btn_acitonbar_right = findViewById(R.id.btn_acitonbar_right);
        tv_actionbar_title = findViewById(R.id.tv_actionbar_title);
        btn_acitonbar_left.setOnClickListener(this);
        tv_actionbar_title.setText("教练详情");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_acitonbar_right:
                finish();
                break;
        }
    }

}
