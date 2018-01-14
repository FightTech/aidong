package com.fanting.aidongtan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.listener.RecycleViewItemClickListener;

/**
 * Created by Administrator on 2017/12/20.
 */

public class HealthDataAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private String[] colors = {"#43a3f6", "#12c402", "#f7426c", "#9442e7", "#f5a200"};
    private RecycleViewItemClickListener itemClickListener;

    public HealthDataAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHoler(LayoutInflater.from(mContext).inflate(R.layout.item_healthdata, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemViewHoler itemViewHoler = (ItemViewHoler) holder;
        itemViewHoler.frameLayout2.setBackgroundColor(Color.parseColor(colors[position]));
        if(itemClickListener!=null){
            itemViewHoler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClickListene(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ItemViewHoler extends RecyclerView.ViewHolder {

        FrameLayout frameLayout2;
        View itemView;

        public ItemViewHoler(View itemView) {
            super(itemView);
            this.itemView=itemView;
            frameLayout2 = itemView.findViewById(R.id.frameLayout2);
        }
    }

    public void setItemClickListener(RecycleViewItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
}
