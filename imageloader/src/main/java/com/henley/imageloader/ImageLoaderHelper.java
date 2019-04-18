package com.henley.imageloader;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.henley.imageloader.glide.GlideImageLoaderStrategy;

import java.io.File;

/**
 * 图片加载辅助类
 *
 * @author Henley
 * @date 2016/12/12 17:56
 */
public class ImageLoaderHelper implements BaseImageLoaderHelper {

    private static Context sContext;
    private ImageLoaderStrategy mDefaultStrategy;
    private ImageLoaderStrategy mCustomStrategy;

    private static class ImageLoaderHelperHolder {
        private static final ImageLoaderHelper INSTANCE = new ImageLoaderHelper();
    }

    private ImageLoaderHelper() {
        if (sContext == null) {
            throw new IllegalArgumentException("Please invoke init() method first.");
        }
        this.mDefaultStrategy = new GlideImageLoaderStrategy(sContext);
        this.setCustomStrategy(mDefaultStrategy);
    }

    public static void init(@NonNull Context context) {
        ImageLoaderHelper.sContext = context.getApplicationContext();
    }

    public static ImageLoaderHelper getInstance() {
        return ImageLoaderHelperHolder.INSTANCE;
    }

    /**
     * 设置默认图片加载策略
     */
    @Override
    public void setDefaultStrategy() {
        this.mCustomStrategy = mDefaultStrategy;
    }

    /**
     * 返回当前图片加载策略
     */
    @Override
    public ImageLoaderStrategy getCustomStrategy() {
        return mCustomStrategy;
    }

    /**
     * 设置自定义图片加载策略
     *
     * @param strategy 图片加载策略
     */
    @Override
    public void setCustomStrategy(ImageLoaderStrategy strategy) {
        this.mCustomStrategy = strategy;
    }

    @Override
    public void loadFromResource(Context context, ImageView target, Integer resourceId, ImageOptions options) {
        mCustomStrategy.loadFromResource(context, target, resourceId, options);
    }

    @Override
    public void loadFromAssets(Context context, ImageView target, String assetName, ImageOptions options) {
        mCustomStrategy.loadFromAssets(context, target, assetName, options);
    }

    @Override
    public void loadFromFile(Context context, ImageView target, File file, ImageOptions options) {
        mCustomStrategy.loadFromFile(context, target, file, options);
    }

    @Override
    public void loadFromUri(Context context, ImageView imageView, Uri uri, ImageOptions options) {
        mCustomStrategy.loadFromUri(context, imageView, uri, options);
    }

    @Override
    public void loadFromUrl(Context context, ImageView target, String url, ImageOptions options) {
        mCustomStrategy.loadFromUrl(context, target, url, options);
    }

    @Override
    public void downloadImage(Context context, String url, ImageOptions options) {
        mCustomStrategy.downloadImage(context, url, options);
    }

    @Override
    public void resumeRequests() {
        mCustomStrategy.resumeRequests();
    }

    @Override
    public void pauseRequests() {
        mCustomStrategy.pauseRequests();
    }

    @Override
    public void cancleRequests(Object object) {
        mCustomStrategy.cancleRequests(object);
    }

    @Override
    public void onLowMemory() {
        mCustomStrategy.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        mCustomStrategy.onTrimMemory(level);
    }

    @Override
    public void clearMemoryCache() {
        mCustomStrategy.clearMemoryCache();
    }

    @Override
    public void clearDiskCache() {
        mCustomStrategy.clearDiskCache();
    }

}
