package com.fanting.aidongtan.holder;

import android.view.View;
import android.widget.TextView;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.model.TextItem;

import butterknife.Bind;

/**
 * @author Wayne
 */
public class TextViewHolder extends BaseViewHolder<TextItem> {

    @Bind(R.id.tv_content)
    TextView tv_content;

    public TextViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onBind(int position, TextItem iItem) {
        //tv_content.setText();
    }
}
