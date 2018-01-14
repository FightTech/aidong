/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fanting.aidongtan.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fanting.aidongtan.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class RentEquipFragment extends Fragment {

    private int mIndex;
    private static int type ;
    private static final int  TYPE_SINGLE=0;
    private static final int  TYPE_COMBINE=1;
    private static final int  TYPE_SUIT=2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_rent_equip, container, false);
        setType();
        setupRecyclerView(rv);
//        if (savedInstanceState == null) {
//            mIndex = getArguments().getInt("index");
//            ((AppCompatActivity) getActivity()).getSupportActionBar()
//                    .setTitle("from arguments:" + mIndex);
//        } else {
//            mIndex = savedInstanceState.getInt("index");
//            ((AppCompatActivity) getActivity()).getSupportActionBar()
//                    .setTitle("from savedInstanceState:" + mIndex);
//        }


//        Point p = new Point();
//        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
//        wm.getDefaultDisplay().getSize(p);
//        //之所以高度是MATCH_PARENT 是因为图片会根据高度居中显示，否则会在屏幕上方
//        rv.setLayoutParams(new LinearLayout.LayoutParams(p.x, ViewGroup.LayoutParams.MATCH_PARENT));


        return rv;
    }

    private void setType() {
        if (getArguments()!=null){
            type = getArguments().getInt("type");
            Log.e("test", "setType: "+ type);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index", mIndex);
        Log.d("test", "call onSaveInstanceState:" + mIndex);
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        SimpleStringRecyclerViewAdapter adapter = new SimpleStringRecyclerViewAdapter(getActivity(),
                getRandomSublist(Cheeses.sCheeseStrings, 30));
        recyclerView.setAdapter(adapter);
        if(type==0){
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        }else{
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        }

        adapter.setOnItemClickListener(new SimpleStringRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                goGoodsDetail();

            }
        });

    }

    private void goGoodsDetail() {
        startActivity(new Intent(getActivity(),GoodsDetailActivity.class));
    }

    private List<String> getRandomSublist(String[] array, int amount) {
        ArrayList<String> list = new ArrayList<>(amount);
        Random random = new Random();
        while (list.size() < amount) {
            list.add(array[random.nextInt(array.length)]);
        }
        return list;
    }

    public static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> implements View.OnClickListener{

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private List<String> mValues;


        public static interface OnRecyclerViewItemClickListener {
            void onItemClick(View view , String data);
        }

        private OnRecyclerViewItemClickListener mOnItemClickListener = null;
        public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
            this.mOnItemClickListener = listener;
        }


        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnItemClickListener.onItemClick(v,(String)v.getTag());
            }
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public String mBoundString;

            public final View mView;
            public final ImageView mImageView;
            public final TextView name;
            public final TextView price;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mImageView = (ImageView) view.findViewById(R.id.image);
                name = (TextView) view.findViewById(R.id.equip_name);
                price = (TextView) view.findViewById(R.id.price);
            }

        }

        public String getValueAt(int position) {
            return mValues.get(position);
        }

        public SimpleStringRecyclerViewAdapter(Context context, List<String> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            if(type==0){
                 view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.equip_list_item, parent, false);
            }else{
                 view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.equip_list_item, parent, false);
            }

            view.setBackgroundResource(mBackground);
            view.setOnClickListener(this);
            return new ViewHolder(view);
        }



        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mBoundString = mValues.get(position);
            if(type==0){
                holder.name.setText("单品");
            }else {
                holder.name.setText("家庭");
            }

            //   holder.mTextView.setText(mValues.get(position));

//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), "Item Clicked!", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            Glide.with(holder.mImageView.getContext())
//                    .load(Cheeses.getRandomCheeseDrawable())
//                    .fitCenter()
//                    .into(holder.mImageView);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }


}
