package com.henley.imageloader.sample;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.henley.imageloader.ImageLoaderHelper;
import com.henley.imageloader.ImageOptions;
import com.henley.imageloader.SimpleImageLoadingListener;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private static final String URL_IMAGE = "https://ae01.alicdn.com/kf/U58f118db979642b5bae964a68f37e23eS.jpg";
    private static final String URL_IMAGE = "https://ae01.alicdn.com/kf/U76a18e0d315e407a8daf3d91de033e31i.jpg";
    private ImageView ivPicture;
    private ImageOptions imageOptions;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivPicture = findViewById(R.id.iv_picture);

        findViewById(R.id.display).setOnClickListener(this);
        findViewById(R.id.download).setOnClickListener(this);

        ImageLoaderHelper.init(getApplicationContext());

        imageOptions = ImageOptions.newBuilder(ImageOptions.getDefaultOptions())
                .setImageLoadingListener(new SimpleImageLoadingListener() {
                    @Override
                    public <ResultType> void onLoadComplete(ImageView imageView, ResultType model) {
                        super.onLoadComplete(imageView, model);
                        if (model instanceof File) {
                            showToast("下载成功！" + ((File) model).getAbsolutePath());
                        } else if (model instanceof Bitmap || model instanceof Drawable) {
                            showToast("加载成功！");
                        }
                    }
                })
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.display:
                ImageLoaderHelper.getInstance().loadFromUrl(this, ivPicture, URL_IMAGE, imageOptions);
                break;
            case R.id.download:
                ImageLoaderHelper.getInstance().downloadImage(this, URL_IMAGE, imageOptions);
                break;
        }
    }

    private void showToast(CharSequence text) {
        if (toast == null) {
            toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

}
