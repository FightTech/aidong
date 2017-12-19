package com.fanting.aidongtan.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.base.BaseActivity;

/**
 * Created by Administrator on 2017/12/18.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_register,tv_tips,tv_tips2;
    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_register);
        tv_register=findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);
        tv_tips=findViewById(R.id.tv_tips);
        tv_tips.setOnClickListener(this);
        iv_back=findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_tips2=findViewById(R.id.tv_tips2);
        tv_tips2.setOnClickListener(this);
        tv_tips2.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG );
        tv_tips2.setText("隐私条款");

        SpannableString spanText=new SpannableString("注册即代表同意爱动弹\t服务条款\t和\t");
        spanText.setSpan(new ClickableSpan() {

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.WHITE);       //设置文件颜色
                ds.setUnderlineText(true);      //设置下划线
            }

            @Override
            public void onClick(View view) {
                Log.d("djj","hehe");
            }
        }, 10,15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_tips.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明，否则会一直出现高亮
        tv_tips.setText(spanText);
        tv_tips.setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_register:
                startActivity(new Intent(this,RegisterSuccessActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_tips2:
              Log.d("djj","haha");
                break;

        }
    }
}
