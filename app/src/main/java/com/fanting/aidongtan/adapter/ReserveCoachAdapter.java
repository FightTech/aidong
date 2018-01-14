package com.fanting.aidongtan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.listener.RecycleViewItemClickListener;

/**
 * Created by Administrator on 2017/12/30.
 */

public class ReserveCoachAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public ReserveCoachAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_reservecoach,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recycleViewItemClickListener!=null){
                    recycleViewItemClickListener.onItemClickListene(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
        }
    }

    private RecycleViewItemClickListener recycleViewItemClickListener;
    public void setOnItemClickListen(RecycleViewItemClickListener recycleViewItemClickListener){
        this.recycleViewItemClickListener=recycleViewItemClickListener;
    }
}
