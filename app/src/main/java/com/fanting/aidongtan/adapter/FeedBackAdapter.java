package com.fanting.aidongtan.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fanting.aidongtan.R;
import com.fanting.aidongtan.listener.AddPicListener;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/10.
 */

public class FeedBackAdapter extends RecyclerView.Adapter<FeedBackAdapter.MyViewHolder> {

    private Context context;
    private List<Uri> datas=new ArrayList<>();
    private AddPicListener listener;
    private int MAX=9;

    public FeedBackAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_add_pic,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if(position==datas.size()){
            holder.iv_delete.setVisibility(View.GONE);
            Glide.with(context).load(R.mipmap.add).centerCrop().into(holder.iv_pic);
            holder.iv_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onPicAdd(v,position);
                    }
                }
            });
        }else{
            final Uri uri=datas.get(position);
            Glide.with(context).load(uri).centerCrop().into(holder.iv_pic);
            if(holder.iv_delete.getVisibility()==View.GONE){
                holder.iv_delete.setVisibility(View.VISIBLE);
                holder.iv_pic.setClickable(false);
            }
            holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datas.remove(uri);
                   notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size()<MAX?datas.size()+1:datas.size();
    }

    public void addPic(Uri cameraFileUri) {
        datas.add(cameraFileUri);
        if(datas.size()>=MAX){
         notifyDataSetChanged();
        }else{
            notifyItemInserted(datas.size()-1);
        }
    }

    public List<Uri> getPics(){
        return datas;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_pic,iv_delete;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv_pic= (ImageView) itemView.findViewById(R.id.iv_pic);
            iv_delete= (ImageView) itemView.findViewById(R.id.iv_delete);
        }
    }

   public void setOnPicAddListener(AddPicListener listener){
        this.listener=listener;
    }

}
