package com.fanting.aidongtan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.fanting.aidongtan.R;

import java.util.List;

/**
 * Created by foryoung on 2017/12/24.
 */

public class PromotionAdapter extends RecycleAbstractAdapter {


    public PromotionAdapter(List<String> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new ListHolder(mHeaderView);
        }
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return new ListHolder(mFooterView);
        }
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promotion, parent, false);
        ImageButton btBuynow = (ImageButton)layout.findViewById(R.id.bt_buynow);
        ImageButton btDetail = (ImageButton)layout.findViewById(R.id.bt_detail);
        btBuynow.setOnClickListener(this);
        btDetail.setOnClickListener(this);
        return new ListHolder(layout);
    }


}
