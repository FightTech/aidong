package com.fanting.aidongtan.holder;

import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.model.BaseItem;

import butterknife.Bind;

/**
 * @author Wayne
 */
public class LookViewHolder extends BaseViewHolder<BaseItem> {

    @Bind(R.id.avtar)
    ImageView imAvtar;

    @Bind(R.id.name)
    TextView tvName;

    @Bind(R.id.image_sex)
    ImageView imSex;

    @Bind(R.id.time)
    TextView tvTime;

    @Bind(R.id.topic)
    TextView tvTopic;

    @Bind(R.id.like)
    TextView tvLike;

    @Bind(R.id.commit)
    TextView tvCommit;

    @Bind(R.id.follow)
    Button btFollow;

    @Bind(R.id.viewsutb_video)
    ViewStub vsVideo;

    @Bind(R.id.viewsutb_image)
    ViewStub vsImage;


    public LookViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onBind(int position, BaseItem iItem) {
//        Glide.with(itemView.getContext())
//                .load(iItem.getCoverUrl())
//                .into(mImageView);
//
//        mTextView.setText(String.format("PicItem %s", position));
    }
}
