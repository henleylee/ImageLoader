package com.henley.imageloader;

import androidx.annotation.DrawableRes;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.target.Target;
import com.henley.imageloader.model.CropType;
import com.henley.imageloader.model.ImageSize;
import com.henley.imageloader.model.ImageType;

/**
 * @author Henley
 * @since 2019/3/29 11:23
 */
public class ImageOptions {

    private static final int RESOURCE_NONE = -1; // 没有占位图
    private String tag; // 唯一标识
    private Target target;
    private Integer placeHolderResId; // 默认占位资源
    private Integer errorResId; // 错误时显示的资源
    private boolean loadOnlyWifi;
    private ImageSize imageSize; // 图片最终显示在ImageView上的宽高度像素
    private int cropType; // 裁剪类型(默认为中部裁剪)
    private int imageType; //图片类型
    private Priority priority;
    private boolean skipMemoryCache;// 是否跳过内存缓存(默认false不跳过)
    private DiskCacheStrategy diskCacheStrategy; // 硬盘缓存(默认为all类型)
    private float thumbnail;// 设置缩略图的缩放比例0.0f-1.0f,如果缩略图比全尺寸图先加载完，就显示缩略图，否则就不显示
    private Integer animResId;// 图片加载完后的动画效果,在异步加载资源完成时会执行该动画。
    private BitmapTransformation[] transformations;
    private ImageLoadingListener requestListener;

    public static ImageOptions getDefaultOptions() {
        return getDefaultOptions(RESOURCE_NONE, RESOURCE_NONE);
    }

    public static ImageOptions getDefaultOptions(@DrawableRes int defaultResId) {
        return getDefaultOptions(defaultResId, defaultResId);
    }

    public static ImageOptions getDefaultOptions(@DrawableRes int errorResId, @DrawableRes int placeHolderResId) {
        return getDefaultOptions(errorResId, placeHolderResId, CropType.CENTER_CROP);
    }

    public static ImageOptions getDefaultOptions(@DrawableRes int errorResId, @DrawableRes int placeHolderResId, @CropType int cropType) {
        return new Builder()
                .setErrorResId(errorResId)
                .setPlaceHolderResId(placeHolderResId)
                .setImageType(ImageType.BITMAP)
                .setImageSize(null)
                .setSkipMemoryCache(false)
                .setDiskCacheStrategy(DiskCacheStrategy.DATA)
                .setCropType(cropType)
                .build();
    }

    public static Builder newBuilder(ImageOptions options) {
        Builder builder = new Builder();
        builder.tag = options.tag;
        builder.target = options.target;
        builder.placeHolderResId = options.placeHolderResId;
        builder.errorResId = options.errorResId;
        builder.imageSize = options.imageSize;
        builder.cropType = options.cropType;
        builder.imageType = options.imageType;
        builder.priority = options.priority;
        builder.skipMemoryCache = options.skipMemoryCache;
        builder.diskCacheStrategy = options.diskCacheStrategy;
        builder.thumbnail = options.thumbnail;
        builder.animResId = options.animResId;
        builder.transformations = options.transformations;
        builder.requestListener = options.requestListener;
        return builder;
    }

    private ImageOptions(Builder builder) {
        this.tag = builder.tag;
        this.target = builder.target;
        this.placeHolderResId = builder.placeHolderResId;
        this.errorResId = builder.errorResId;
        this.loadOnlyWifi = builder.loadOnlyWifi;
        this.imageSize = builder.imageSize;
        this.cropType = builder.cropType;
        this.imageType = builder.imageType;
        this.priority = builder.priority;
        this.skipMemoryCache = builder.skipMemoryCache;
        this.diskCacheStrategy = builder.diskCacheStrategy;
        this.thumbnail = builder.thumbnail;
        this.animResId = builder.animResId;
        this.transformations = builder.transformations;
        this.requestListener = builder.requestListener;
    }

    public String getTag() {
        return tag;
    }

    public Integer getPlaceHolderResId() {
        return placeHolderResId;
    }

    public Integer getErrorResId() {
        return errorResId;
    }

    public ImageSize getImageSize() {
        return imageSize;
    }

    public int getCropType() {
        return cropType;
    }

    public int getImageType() {
        return imageType;
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isSkipMemoryCache() {
        return skipMemoryCache;
    }

    public boolean isLoadOnlyWifi() {
        return loadOnlyWifi;
    }

    public DiskCacheStrategy getDiskCacheStrategy() {
        return diskCacheStrategy;
    }

    public float getThumbnail() {
        return thumbnail;
    }

    public Integer getAnimResId() {
        return animResId;
    }

    public BitmapTransformation[] getTransformations() {
        return transformations;
    }

    public ImageLoadingListener getRequestListener() {
        return requestListener;
    }

    /**
     * Builder类
     */
    public static class Builder {

        private String tag; //唯一标识
        private Target target;
        private int placeHolderResId; //默认占位资源
        private int errorResId; //错误时显示的资源
        private boolean loadOnlyWifi;
        private ImageSize imageSize; //图片最终显示在ImageView上的宽高度像素
        private int cropType; // 裁剪类型,默认为中部裁剪
        private int imageType; //true,强制显示的是gif动画,如果url不是gif的资源,那么会回调error()
        private Priority priority;
        private boolean skipMemoryCache;//是否跳过内存缓存,默认false不跳过
        private DiskCacheStrategy diskCacheStrategy; //硬盘缓存,默认为all类型
        private float thumbnail;//设置缩略图的缩放比例0.0f-1.0f,如果缩略图比全尺寸图先加载完，就显示缩略图，否则就不显示
        private Integer animResId;//图片加载完后的动画效果,在异步加载资源完成时会执行该动画。
        private BitmapTransformation[] transformations;
        private ImageLoadingListener requestListener;

        public Builder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder setTarget(Target target) {
            this.target = target;
            return this;
        }

        public Builder setPlaceHolderResId(@DrawableRes int placeHolderResId) {
            this.placeHolderResId = placeHolderResId;
            return this;
        }

        public Builder setErrorResId(@DrawableRes int errorResId) {
            this.errorResId = errorResId;
            return this;
        }

        public Builder setLoadOnlyWifi(boolean loadOnlyWifi) {
            this.loadOnlyWifi = loadOnlyWifi;
            return this;
        }

        public Builder setImageSize(ImageSize imageSize) {
            this.imageSize = imageSize;
            return this;
        }

        public Builder setCropType(@CropType int cropType) {
            this.cropType = cropType;
            return this;
        }

        public Builder setImageType(int imageType) {
            this.imageType = imageType;
            return this;
        }

        public Builder setPriority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder setSkipMemoryCache(boolean skipMemoryCache) {
            this.skipMemoryCache = skipMemoryCache;
            return this;
        }

        public Builder setDiskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
            this.diskCacheStrategy = diskCacheStrategy;
            return this;
        }

        public Builder setThumbnail(float thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public Builder setAnimResId(Integer animResId) {
            this.animResId = animResId;
            return this;
        }

        public Builder setTransformations(BitmapTransformation[] transformations) {
            this.transformations = transformations;
            return this;
        }

        public Builder setImageLoadingListener(ImageLoadingListener requestListener) {
            this.requestListener = requestListener;
            return this;
        }

        public ImageOptions build() {
            return new ImageOptions(this);
        }
    }

}