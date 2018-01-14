package com.fanting.aidongtan.holder;

import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fanting.aidongtan.R;
import com.fanting.aidongtan.model.PicItem;

import butterknife.Bind;

/**
 * @author Wayne
 */
public class PicViewHolder extends BaseViewHolder<PicItem> {

    @Bind(R.id.iv_pic)
    ImageView iv_pic;

    @Bind(R.id.tv_content)
    TextView mTextView;



    public PicViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onBind(int position, PicItem iItem) {
        Glide.with(itemView.getContext())
                .load(iItem.getCoverUrl())
                .into(iv_pic);

       // mTextView.setText(String.format("PicItem %s", position));
    }
}
