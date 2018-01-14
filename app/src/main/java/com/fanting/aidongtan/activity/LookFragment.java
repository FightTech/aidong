package com.fanting.aidongtan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.holder.BaseViewHolder;
import com.fanting.aidongtan.holder.ViewHolderFactory;
import com.fanting.aidongtan.model.BaseItem;
import com.fanting.aidongtan.utils.ItemUtils;
import com.waynell.videolist.visibility.calculator.SingleListViewItemActiveCalculator;
import com.waynell.videolist.visibility.items.ListItem;
import com.waynell.videolist.visibility.scroll.ItemsProvider;
import com.waynell.videolist.visibility.scroll.RecyclerViewItemPositionGetter;

import java.util.List;

public class LookFragment extends Fragment implements View.OnClickListener {


    RecyclerView mRecyclerView;

    private int mScrollState;

    private SingleListViewItemActiveCalculator mCalculator;
    private Button btn_acitonbar_left, btn_acitonbar_right;
    private TextView tv_actionbar_title;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_look, null);

        btn_acitonbar_left = view.findViewById(R.id.btn_acitonbar_left);
        btn_acitonbar_left.setVisibility(View.GONE);
        btn_acitonbar_right = view.findViewById(R.id.btn_acitonbar_right);
        tv_actionbar_title = view.findViewById(R.id.tv_actionbar_title);
        btn_acitonbar_left.setOnClickListener(this);
        tv_actionbar_title.setText("爱看");
        btn_acitonbar_right.setBackground(getResources().getDrawable(R.mipmap.fabiao));
        btn_acitonbar_right.setOnClickListener(this);

        mRecyclerView =  view.findViewById(R.id.recycler_view);
        final MyAdapter adapter = new MyAdapter();
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mCalculator = new SingleListViewItemActiveCalculator(adapter,
                new RecyclerViewItemPositionGetter(layoutManager, mRecyclerView));
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                mScrollState = newState;
                if (newState == RecyclerView.SCROLL_STATE_IDLE && adapter.getItemCount() > 0) {
                    mCalculator.onScrollStateIdle();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                mCalculator.onScrolled(mScrollState);
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_acitonbar_right:
                startActivity(new Intent(getActivity(),PublishActivity.class));
                break;
        }

    }


    private class MyAdapter extends RecyclerView.Adapter<BaseViewHolder<? extends BaseItem>>
            implements ItemsProvider {

        private List<? extends BaseItem> mListItems;

        public MyAdapter() {
            mListItems = ItemUtils.generateMockData();
        }

        @Override
        public BaseViewHolder<? extends BaseItem> onCreateViewHolder(ViewGroup parent, int viewType) {
            return ViewHolderFactory.buildViewHolder(parent, viewType);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position) {
            BaseItem baseItem = mListItems.get(position);
            holder.onBind(position, baseItem);
        }

        @Override
        public int getItemCount() {
            return mListItems.size();
        }

        @Override
        public int getItemViewType(int position) {
            return mListItems.get(position).getViewType();
        }

        @Override
        public ListItem getListItem(int position) {
            RecyclerView.ViewHolder holder = mRecyclerView.findViewHolderForAdapterPosition(position);
            if (holder instanceof ListItem) {
                return (ListItem) holder;
            }
            return null;
        }

        @Override
        public int listItemSize() {
            return getItemCount();
        }


    }

}
