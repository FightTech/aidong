package com.fanting.aidongtan.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fanting.aidongtan.R;
import com.fanting.aidongtan.base.BaseActivity;
import com.fanting.aidongtan.constant.MyConstant;
import com.fanting.aidongtan.utils.GlideUtil;
import com.fanting.aidongtan.widgets.wheelview.WheelView;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/18.
 */

public class RegisterSuccessActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private TextView tv_complete, tv_age;
    private ImageView iv_back, iv_head;
    private RadioGroup rg_gender;
    private RadioButton rb_man, rb_woman;
    private RelativeLayout rl_age;
    private final int CONSULT_DOC_PICTURE = 1000;
    private final int CONSULT_DOC_CAMERA = 1001;
    private final int CORP_CAMERA_IMAGE = 1002;
    private Uri cameraFileUri;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_register_success);
        tv_complete = findViewById(R.id.tv_complete);
        tv_complete.setOnClickListener(this);

        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        rl_age = findViewById(R.id.rl_age);
        rl_age.setOnClickListener(this);
        iv_head = findViewById(R.id.iv_head);
        iv_head.setOnClickListener(this);

        rg_gender = findViewById(R.id.rg_gender);
        rb_man = findViewById(R.id.rb_man);
        rb_woman = findViewById(R.id.rb_woman);
        rg_gender.setOnCheckedChangeListener(this);

        tv_age = findViewById(R.id.tv_age);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_complete:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_age:
                setAge();
                break;
            case R.id.iv_head:
                setHeadPic();
                break;
            case R.id.tv_camera:
                //填写事件
                File file = new File(MyConstant.PHOTO_PATH, System.currentTimeMillis()+".jpg");
                cameraFileUri = Uri.fromFile(file);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraFileUri);
                startActivityForResult(intent, CONSULT_DOC_CAMERA);
                break;
            case R.id.tv_photo:
                Crop.pickImage(this, CONSULT_DOC_PICTURE);
                break;
        }
    }

    private void setHeadPic() {
        View outerView = LayoutInflater.from(this).inflate(R.layout.layout_choose_pic, null);
        outerView.findViewById(R.id.tv_camera).setOnClickListener(this);
        outerView.findViewById(R.id.tv_photo).setOnClickListener(this);
        alertDialog=new AlertDialog.Builder(this)
                .setView(outerView)
                .show();
    }


    private void setAge() {
        View outerView = LayoutInflater.from(this).inflate(R.layout.layout_wheelview, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(outerView)
                .show();
        final WheelView wv = outerView.findViewById(R.id.wheel_view_wv);
        wv.setItems(getNumbers(), 0);//init selected position is 0 初始选中位置为0
        wv.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                tv_age.setText("年龄：" + item);
                if (alertDialog != null) {
                    alertDialog.dismiss();
                }
                Log.d("djj", "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
    }

    private ArrayList getNumbers() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            list.add(i + "");
        }
        return list;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getId()) {
            case R.id.rb_man:
                //rb_man.setSelected(true);
                break;
            case R.id.rb_woman:
                //rb_woman.setSelected(true);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(alertDialog!=null&&alertDialog.isShowing()){
            alertDialog.dismiss();
        }
        switch (requestCode){
            case CONSULT_DOC_PICTURE:
                if (data == null) {
                    Toast.makeText(this, "请重新选择图片", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Uri outputUri = Uri.fromFile(File.createTempFile("corp", ".jpg"));
                    cameraFileUri = data.getData();
                    Crop.of(cameraFileUri, outputUri).asSquare()
                            .start(this, CORP_CAMERA_IMAGE);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case CONSULT_DOC_CAMERA:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri outputUri = Uri.fromFile(File.createTempFile("corp", ".jpg"));
                        Crop.of(cameraFileUri, outputUri).asSquare()
                                .start(this, CORP_CAMERA_IMAGE);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case CORP_CAMERA_IMAGE:
                if (data != null) {
                    Uri uri = Crop.getOutput(data);
                    if (uri != null) {
                        Log.i("djj",uri.getPath());
                        GlideUtil.loadCircleImageView(this,uri.getPath(),iv_head);
                    } else {
                        Toast.makeText(this, "请重新选择图片", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请重新选择图片", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
