package com.fanting.aidongtan.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.fanting.aidongtan.R;

/**
 * Created by foryoung on 2017/11/30.
 */

public class FlipViewGroup extends RelativeLayout {

    public FlipViewGroup(Context context) {
        super(context);
        setLayout(context);
    }

    public FlipViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayout(context);
    }

    private void setLayout(Context context){
        LayoutInflater.from(context).inflate(R.layout.layout_flip_map, this);
    }




}
