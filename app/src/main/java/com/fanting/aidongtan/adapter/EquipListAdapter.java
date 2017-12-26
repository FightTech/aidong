package com.fanting.aidongtan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanting.aidongtan.R;

import java.util.List;

/**
 * Created by foryoung on 2017/12/24.
 */

public class EquipListAdapter extends RecycleAbstractAdapter {


    public EquipListAdapter(List<String> list) {
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
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equip, parent, false);
        return new ListHolder(layout);
    }


}
