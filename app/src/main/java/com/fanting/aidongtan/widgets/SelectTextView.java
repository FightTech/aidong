package com.fanting.aidongtan.widgets;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.fanting.aidongtan.R;

/**
 * Created by Administrator on 2018/1/2.
 */

public class SelectTextView extends TextView {
    private boolean isSelected;

    public SelectTextView(Context context) {
        super(context);
    }

    public SelectTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackground(context.getResources().getDrawable(R.drawable.selector_bg_text));
    }

    public SelectTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackground(context.getResources().getDrawable(R.drawable.selector_bg_text));
    }

    public SelectTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setBackground(context.getResources().getDrawable(R.drawable.selector_bg_text));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("djj",isSelected+"");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isSelected) {
                    this.setSelected(false);
                    this.setTextColor(Color.parseColor("#8f8f8f"));
                } else {
                    this.setSelected(true);
                    this.setTextColor(Color.WHITE);
                }
                isSelected=!isSelected;
                break;
        }
         return super.onTouchEvent(event);
    }

   /* @Override
    public void setSelected(boolean selected) {
        if(isSelected){
            this.setTextColor(Color.WHITE);
        }else{
            this.setTextColor(Color.parseColor("#8f8f8f"));
        }
        isSelected = selected;
        super.setSelected(selected);
    }*/
   public void setSelect(boolean selected){
       if(selected){
           this.setTextColor(Color.WHITE);
       }else{
           this.setTextColor(Color.parseColor("#8f8f8f"));
       }
       isSelected = selected;
       super.setSelected(selected);
   }
}
