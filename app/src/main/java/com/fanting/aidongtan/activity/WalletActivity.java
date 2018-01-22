package com.fanting.aidongtan.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.base.BaseActivity;
import com.fanting.aidongtan.utils.DisplayUtil;

/**
 * Created by Administrator on 2018/1/14.
 */

public class WalletActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_acitonbar_left, btn_acitonbar_right;
    private TextView tv_actionbar_title;
    private RelativeLayout rl_chongzhi, rl_tixian;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_wallet);

        btn_acitonbar_left = findViewById(R.id.btn_acitonbar_left);
        btn_acitonbar_right = findViewById(R.id.btn_acitonbar_right);
        tv_actionbar_title = findViewById(R.id.tv_actionbar_title);
        btn_acitonbar_right.setVisibility(View.VISIBLE);
        btn_acitonbar_left.setOnClickListener(this);
        tv_actionbar_title.setText("钱包");
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DisplayUtil.dip2px(this, 5), 0, 0);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        btn_acitonbar_right.setLayoutParams(params);
        btn_acitonbar_right.setTextColor(Color.WHITE);
        btn_acitonbar_right.setTextSize(DisplayUtil.sp2px(this, 4));
        btn_acitonbar_right.setText("交易记录");
        btn_acitonbar_right.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_acitonbar_right.setOnClickListener(this);

        rl_chongzhi = findViewById(R.id.rl_chongzhi);
        rl_chongzhi.setOnClickListener(this);
        rl_tixian = findViewById(R.id.rl_tixian);
        rl_tixian.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_acitonbar_left:
                finish();
                break;
            case R.id.btn_acitonbar_right:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.rl_chongzhi:
                startActivity(new Intent(this, RechargeActivity.class));
                break;
        }
    }
}
