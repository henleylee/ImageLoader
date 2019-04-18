package com.henley.imageloader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import java.io.File;

/**
 * 图片加载策略接口
 *
 * @author Henley
 * @date 2016/12/12 16:45
 */
public interface ImageLoaderStrategy {

    /**
     * 加载资源图片
     *
     * @param context    上下文
     * @param imageView  ImageView对象
     * @param resourceId 图片资源ID
     * @param options    图片加载配置选项
     */
    void loadFromResource(Context context, ImageView imageView, Integer resourceId, ImageOptions options);

    /**
     * 加载Assets图片
     *
     * @param context   上下文
     * @param imageView ImageView对象
     * @param assetName Assets图片名称
     * @param options   图片加载配置选项
     */
    void loadFromAssets(Context context, ImageView imageView, String assetName, ImageOptions options);

    /**
     * 加载本地图片
     *
     * @param context   上下文
     * @param imageView ImageView对象
     * @param file      本地图片文件
     * @param options   图片加载配置选项
     */
    void loadFromFile(Context context, ImageView imageView, File file, ImageOptions options);

    /**
     * 加载指定URI的图片
     *
     * @param context   上下文
     * @param imageView ImageView对象
     * @param uri       图片Uri
     * @param options   图片加载配置选项
     */
    void loadFromUri(Context context, ImageView imageView, Uri uri, ImageOptions options);

    /**
     * 加载网络图片
     *
     * @param context   上下文
     * @param imageView ImageView对象
     * @param url       图片地址Url
     * @param options   图片加载配置选项
     */
    void loadFromUrl(Context context, ImageView imageView, String url, ImageOptions options);

    /**
     * 下载图片
     *
     * @param context 上下文
     * @param url     图片地址Url
     * @param options 图片加载配置选项
     */
    void downloadImage(Context context, String url, ImageOptions options);

    /**
     * 恢复请求
     */
    void resumeRequests();

    /**
     * 暂停请求
     */
    void pauseRequests();

    /**
     * 取消请求
     */
    void cancleRequests(Object object);

    /**
     * 在系统内存不足，所有后台程序都被杀死时，系统会调用该方法
     */
    void onLowMemory();

    /**
     * 系统会根据不同的内存状态，响应不同的内存释放策略
     */
    void onTrimMemory(int level);

    /**
     * 清除内存缓存
     */
    void clearMemoryCache();

    /**
     * 清除磁盘缓存
     */
    void clearDiskCache();

}
