package com.henley.imageloader.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ObjectKey;
import com.henley.imageloader.ImageLoaderStrategy;
import com.henley.imageloader.ImageLoadingListener;
import com.henley.imageloader.ImageOptions;
import com.henley.imageloader.Utils;
import com.henley.imageloader.model.CropType;
import com.henley.imageloader.model.ImageSize;
import com.henley.imageloader.model.ImageType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Glide图片加载策略
 *
 * @author Henley
 * @date 2016/12/12 16:52
 */
public class GlideImageLoaderStrategy implements ImageLoaderStrategy {

    private Context mContext;

    public GlideImageLoaderStrategy(@NonNull Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override
    public void loadFromResource(Context context, ImageView imageView, Integer resourceId, ImageOptions options) {
        load(context, imageView, resourceId, options);
    }

    @Override
    public void loadFromAssets(Context context, ImageView imageView, String assetName, ImageOptions options) {
        load(context, imageView, "file:///android_asset/" + assetName, options);
    }

    @Override
    public void loadFromFile(Context context, ImageView imageView, File file, ImageOptions options) {
        load(context, imageView, file, options);
    }

    @Override
    public void loadFromUri(Context context, ImageView imageView, Uri uri, ImageOptions options) {
        load(context, imageView, uri, options);
    }

    @Override
    public void loadFromUrl(Context context, ImageView imageView, String url, ImageOptions options) {
        load(context, imageView, url, options);
    }

    @Override
    public void downloadImage(Context context, String url, ImageOptions options) {
        download(context, url, options);
    }

    @Override
    public void resumeRequests() {
        Glide.with(mContext).resumeRequests();
    }

    @Override
    public void pauseRequests() {
        Glide.with(mContext).pauseRequests();
    }

    @Override
    public void cancleRequests(Object object) {
        if (object instanceof View) {
            Glide.with(mContext).clear((View) object);
        } else if (object instanceof Target) {
            Glide.with(mContext).clear((Target<?>) object);
        }
    }

    @Override
    public void onLowMemory() {
        if (!Utils.isOnMainThread()) {
            throw new IllegalArgumentException("You must call this method on the main thread.");
        }
        Glide.get(mContext).onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        if (!Utils.isOnMainThread()) {
            throw new IllegalArgumentException("You must call this method on the main thread.");
        }
        Glide.get(mContext).onTrimMemory(level);
    }

    @Override
    public void clearMemoryCache() {
        if (!Utils.isOnMainThread()) {
            throw new IllegalArgumentException("You must call this method on the main thread.");
        }
        Glide.get(mContext).clearMemory();
    }

    @Override
    public void clearDiskCache() {
        if (Utils.isOnMainThread()) {
            throw new IllegalArgumentException("You must call this method on a background thread.");
        }
        Glide.get(mContext).clearDiskCache();
    }

    private <ModelType> void load(Context context, ImageView imageView, ModelType model, ImageOptions options) {
        if (model == null) {
            throw new IllegalArgumentException("model is null.");
        }
        RequestManager requestManager = Glide.with(context);
        if (options != null) {
            int imageType = options.getImageType();
            if (imageType == ImageType.BITMAP) {
                load(requestManager.asBitmap(), imageView, model, options);
            } else if (imageType == ImageType.DRAWABLE) {
                load(requestManager.asDrawable(), imageView, model, options);
            } else if (imageType == ImageType.GIF) {
                load(requestManager.asGif(), imageView, model, options);
            } else if (imageType == ImageType.FILE) {
                load(requestManager.asFile(), imageView, model, options);
            } else {
                load(requestManager.asDrawable(), imageView, model, options);
            }
        } else {
            load(requestManager.asDrawable(), imageView, model, null);
        }
    }

    private <ModelType, ResultType> void load(RequestBuilder<ResultType> requestBuilder, ImageView imageView, ModelType model, ImageOptions options) {
        boolean loadOnlyWifi = options != null && options.isLoadOnlyWifi();
        boolean loadThumbnail = options != null && options.getThumbnail() > 0;
        final ImageLoadingListener listener = options == null ? null : options.getRequestListener();
        requestBuilder.apply(generateRequestOptions(model, options))
                .onlyRetrieveFromCache(loadOnlyWifi && !Utils.isWiFiAvailable(mContext))
                .thumbnail(loadThumbnail ? options.getThumbnail() : 1.0f)
                .load(model)
                .addListener(new GlideRequestListener<ResultType>(listener))
                .into(new ImageViewTarget<ResultType>(imageView) {
                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        if (listener != null) {
                            listener.onLoadingStarted(getView());
                        }
                    }

                    @Override
                    protected void setResource(@Nullable ResultType resource) {
                        if (resource == null) {
                            getView().setImageDrawable(null);
                            return;
                        }
                        if (resource instanceof Bitmap) {
                            getView().setImageBitmap((Bitmap) resource);
                        } else if (resource instanceof Drawable) {
                            getView().setImageDrawable((Drawable) resource);
                        } else if (resource instanceof File) {
                            try {
                                getView().setImageBitmap(BitmapFactory.decodeStream(new FileInputStream((File) resource)));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                })
        ;
    }

    private void download(Context context, String url, ImageOptions options) {
        if (url == null || url.length() == 0) {
            throw new IllegalArgumentException("model is null.");
        }
        final ImageLoadingListener listener = options == null ? null : options.getRequestListener();
        Glide.with(context)
                .applyDefaultRequestOptions(generateRequestOptions(url, options))
                .download(url)
                .addListener(new GlideRequestListener<File>(listener))
                .into(new RequestFutureTarget<File>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {

                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        if (listener != null) {
                            listener.onLoadingStarted(null);
                        }
                    }
                });
    }

    /**
     * 初始化图片加载配置选项
     *
     * @param <ModelType> 数据模型泛型
     * @param model       数据模型
     * @param options     图片加载配置选项
     */
    @NonNull
    @SuppressLint("CheckResult")
    private <ModelType> RequestOptions generateRequestOptions(ModelType model, ImageOptions options) {
        RequestOptions requestOptions = new RequestOptions();
        if (options == null) {
            return requestOptions;
        }
        if (options.getPlaceHolderResId() != null && options.getPlaceHolderResId() != -1) {
            requestOptions.placeholder(options.getPlaceHolderResId()); // 设置加载中的占位图
        }
        if (options.getErrorResId() != null && options.getErrorResId() != -1) {
            requestOptions.error(options.getErrorResId()); // 设置加载失败的占位图
        }
        ImageSize imageSize = options.getImageSize();
        if (imageSize != null && imageSize.isValidity()) {
            requestOptions.override(imageSize.getWidth(), imageSize.getHeight()); // 设置加载的图片大小
        } else {
            requestOptions.override(Target.SIZE_ORIGINAL);
        }
        if (options.getTag() != null) {
            requestOptions.signature(new ObjectKey(options.getTag())); // 设置图片标识
        } else if (model != null && !TextUtils.isEmpty(model.toString())) {
            requestOptions.signature(new ObjectKey(model)); // 设置图片标识
        }
        if (options.getPriority() != null) {
            requestOptions.priority(options.getPriority()); // 设置图片加载优先级(优先级高的先加载，优先级低的后加载)
        }
        if (options.isSkipMemoryCache()) {
            requestOptions.skipMemoryCache(options.isSkipMemoryCache()); // 设置是否跳过内存缓存
        }
        if (options.getDiskCacheStrategy() != null) {
            requestOptions.diskCacheStrategy(options.getDiskCacheStrategy()); // 设置磁盘缓存策略
        }
        if (options.getCropType() == CropType.CENTER_INSIDE) {
            requestOptions.centerInside();
        } else if (options.getCropType() == CropType.CENTER_CROP) {
            requestOptions.centerCrop();
        } else if (options.getCropType() == CropType.FIT_CENTER) {
            requestOptions.fitCenter();
        }
        if (options.getTransformations() != null) {
            requestOptions.transform(options.getTransformations()); // 设置动态转换
        }
        return requestOptions;
    }

    private static class GlideRequestListener<ResultType> implements RequestListener<ResultType> {

        private ImageLoadingListener listener;

        public GlideRequestListener(ImageLoadingListener listener) {
            this.listener = listener;
        }

        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<ResultType> target, boolean isFirstResource) {
            if (listener != null) {
                listener.onLoadFailed(null, e);
            }
            return false;
        }

        @Override
        public boolean onResourceReady(ResultType resource, Object model, Target<ResultType> target, DataSource dataSource, boolean isFirstResource) {
            if (listener != null) {
                listener.onLoadComplete(null, resource);
            }
            return false;
        }
    }

}
