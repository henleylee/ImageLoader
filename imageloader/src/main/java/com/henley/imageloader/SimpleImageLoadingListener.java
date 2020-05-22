package com.henley.imageloader;

import android.widget.ImageView;

/**
 * 图片加载监听接口的空实现
 *
 * @author Henley
 * @since 2019/4/18 17:47
 */
public class SimpleImageLoadingListener implements ImageLoadingListener {

    @Override
    public void onLoadingStarted(ImageView imageView) {

    }

    @Override
    public void onLoadFailed(ImageView imageView, Exception e) {

    }

    @Override
    public <ResultType> void onLoadComplete(ImageView imageView, ResultType model) {

    }

}
