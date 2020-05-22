package com.henley.imageloader.glide;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.NonNull;

/**
 * Glide配置
 *
 * @author Henley
 * @since 2019/3/29 16:55
 */
@GlideModule
public class ImageGlideModule extends AppGlideModule {

    private static final String TAG = "GlideModelConfig";
    private static final String DISK_CACHE_NAME = "images";
    private static final int DISK_CACHE_SIZE = 200 * 1024 * 1024; // 200MB

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
        Log.i(TAG, "AppGlideModule初始化...");

        // 设置日志级别
        builder.setLogLevel(Log.DEBUG);

        // 设置内存缓存和Bitmap池
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();
        builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));
        builder.setBitmapPool(new LruBitmapPool(calculator.getBitmapPoolSize()));

        // 设置磁盘缓存
        builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(
                context,
                DISK_CACHE_NAME,
                DISK_CACHE_SIZE
        ));

        // 设置默认的请求选项
        builder.setDefaultRequestOptions(
                new RequestOptions()
                        .centerCrop()                               // 设置转换类型
                        .dontAnimate()                              // 设置禁用动画
                        .format(DecodeFormat.PREFER_ARGB_8888)      // 设置图片格式
        );

        // 设置处理未捕获异常的策略
        builder.setSourceExecutor(GlideExecutor.newSourceExecutor());
        builder.setDiskCacheExecutor(GlideExecutor.newDiskCacheExecutor());
    }

}