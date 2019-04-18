package com.henley.imageloader;

import android.widget.ImageView;

/**
 * 图片加载监听接口
 *
 * @author Henley
 * @date 2017/2/9 13:43
 */
public interface ImageLoadingListener {

    void onLoadingStarted(ImageView imageView);

    void onLoadFailed(ImageView imageView, Exception e);

    <ResultType> void onLoadComplete(ImageView imageView, ResultType model);

}
