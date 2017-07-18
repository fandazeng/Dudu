package com.zeng.fanda.dudu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.zeng.fanda.mylibrary.core.imageloader.ImageLoader01plus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by David on 2017/7/17.
 */

public class ImageLoaderTestActivity extends AppCompatActivity {


    @BindView(R.id.iv_image)
    ImageView mImageView;

    ImageLoader01plus mImageLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_load_image)
    public void loadImage() {
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader01plus();
        }
        String url = "http://img02.tooopen.com/images/20141231/sy_78327074576.jpg";
        mImageLoader.displayImage(url, mImageView);
//        Glide.with(this).load(url).into(mImageView);

    }
}
