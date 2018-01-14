package com.fanting.aidongtan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.listener.RecycleViewItemClickListener;
import com.fanting.aidongtan.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 */

public class TrainingAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<String> data;
    private int widht,height,margin;
    private List<Integer> selectedPosition=new ArrayList<>();

    public TrainingAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
        widht=  DisplayUtil.dip2px(context,70);
        height= DisplayUtil.dip2px(context,30);
        margin=DisplayUtil.dip2px(context,10);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView=new TextView(context);
        textView.setGravity(Gravity.CENTER);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(widht,height);
        layoutParams.setMargins(margin,margin/2,margin,margin/2);
        textView.setLayoutParams(layoutParams);
        textView.setBackground(context.getResources().getDrawable(R.drawable.selector_bg_text));
        return new MyViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder  myViewHolder= (MyViewHolder) holder;
        myViewHolder.textView.setText(data.get(position));
        myViewHolder.textView.setSelected(false);
        myViewHolder.textView.setTextColor(context.getResources().getColor(R.color.gray_8f));
        myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( myViewHolder.textView.isSelected()){
                    myViewHolder.textView.setSelected(false);
                    myViewHolder.textView.setTextColor(context.getResources().getColor(R.color.gray_8f));
                    selectedPosition.remove(new Integer(position));
                }else{
                    myViewHolder.textView.setSelected(true);
                    myViewHolder.textView.setTextColor(context.getResources().getColor(R.color.white));
                    selectedPosition.add(new Integer(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView;
        }
    }

    public void reset(){
        for (int positon :selectedPosition) {
            this.notifyItemChanged(positon);
        }
    }

    public List<Integer> getSelectedPosition(){
        return selectedPosition;
    }
}
