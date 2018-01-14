package com.fanting.aidongtan.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fanting.aidongtan.R;
import com.fanting.aidongtan.adapter.FeedBackAdapter;
import com.fanting.aidongtan.base.BaseActivity;
import com.fanting.aidongtan.constant.MyConstant;
import com.fanting.aidongtan.listener.AddPicListener;
import com.fanting.aidongtan.utils.DisplayUtil;
import com.fanting.aidongtan.widgets.ActionSheetDialog;
import com.soundcloud.android.crop.Crop;

import java.io.File;

/**
 * Created by Administrator on 2018/1/14.
 */

public class PublishActivity extends BaseActivity implements View.OnClickListener, AddPicListener {
    RecyclerView rv_addpic;
    private Button btn_acitonbar_left, btn_acitonbar_right;
    private TextView tv_actionbar_title;
    private FeedBackAdapter adapter;

    private final int CONSULT_DOC_PICTURE = 1000;
    private final int CONSULT_DOC_CAMERA = 1001;
    private Uri cameraFileUri;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_publish);

        btn_acitonbar_right = findViewById(R.id.btn_acitonbar_right);
        btn_acitonbar_left = findViewById(R.id.btn_acitonbar_left);
        tv_actionbar_title = findViewById(R.id.tv_actionbar_title);
        btn_acitonbar_left.setOnClickListener(this);
        tv_actionbar_title.setText("发布内容");

        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DisplayUtil.dip2px(this,5),0,0);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        btn_acitonbar_right.setLayoutParams(params);
        btn_acitonbar_right.setTextColor(Color.WHITE);
        btn_acitonbar_right.setTextSize(DisplayUtil.sp2px(this,4));
        btn_acitonbar_right.setText("发布");
        btn_acitonbar_right.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_acitonbar_right.setOnClickListener(this);

        rv_addpic=findViewById(R.id.rv_addpic);
        adapter=new FeedBackAdapter(this);
        adapter.setOnPicAddListener(this);
        rv_addpic.setLayoutManager(new GridLayoutManager(this,3));
        rv_addpic.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_acitonbar_left:
                finish();
                break;
            case R.id.btn_acitonbar_right:
                finish();
                break;
        }
    }

    @Override
    public void onPicAdd(View v, int position) {
        new ActionSheetDialog(this)
                .builder()
                .setTitle("选择图片")
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("相册", ActionSheetDialog.SheetItemColor.Blue
                        , new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Crop.pickImage(PublishActivity.this, CONSULT_DOC_PICTURE);
                            }
                        })
                .addSheetItem("照相机", ActionSheetDialog.SheetItemColor.Blue
                        , new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                //填写事件
                                File file = new File(MyConstant.PHOTO_PATH, System.currentTimeMillis()+".jpg");
                                cameraFileUri = Uri.fromFile(file);
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraFileUri);
                                startActivityForResult(intent, CONSULT_DOC_CAMERA);
                            }
                        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case CONSULT_DOC_PICTURE:
                if(data == null){
                    Toast.makeText(this, "请重新选择图片", Toast.LENGTH_SHORT).show();
                    return;
                }
                cameraFileUri= data.getData();
                adapter.addPic(cameraFileUri);
                break;
            case CONSULT_DOC_CAMERA:
                if(resultCode!=RESULT_OK){
                    Toast.makeText(this, "请重新选择图片", Toast.LENGTH_SHORT).show();
                    return;
                }
                adapter.addPic(cameraFileUri);
                break;
        }
    }

}
