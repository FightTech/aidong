package com.fanting.aidongtan.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fanting.aidongtan.model.BaseItem;

import butterknife.ButterKnife;

/**
 * @author Wayne
 */
public abstract class BaseViewHolder<T extends BaseItem> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void onBind(int position, T iItem);
}
