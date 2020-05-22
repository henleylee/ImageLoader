# ImageLoader —— Android 图片加载框架封装(策略模式)

## 特点 ##
 - 低耦合，方便将来的代码扩展；
 - 满足项目中各种需求，调用方便；
 - 支持目前市场上使用率最高的图片框架 Glide、Fresco、Picasso 之间的切换

## Download ##
### Gradle ###
```gradle
dependencies {
    implementation 'com.henley.android:imageloader:1.0.2'
}
```

### APK Demo ###

下载 [APK-Demo](https://github.com/HenleyLee/ImageLoader/raw/master/app/app-release.apk)

## 使用 ##
### 初始化 ###
```java
ImageLoaderHelper.init(Context context);
```

### 设置图片加载策略 ###
```java
ImageLoaderHelper.getInstance().setCustomStrategy(ImageLoaderStrategy strategy);
```

### 加载图片 ###
```java
ImageLoaderHelper.getInstance().loadFromResource(Context context, ImageView target, Integer resourceId, ImageOptions options);
ImageLoaderHelper.getInstance().loadFromAssets(Context context, ImageView target, String assetName, ImageOptions options);
ImageLoaderHelper.getInstance().loadFromFile(Context context, ImageView target, File file, ImageOptions options);
ImageLoaderHelper.getInstance().loadFromUri(Context context, ImageView imageView, Uri uri, ImageOptions options);
ImageLoaderHelper.getInstance().loadFromUrl(Context context, ImageView target, String url, ImageOptions options);
```

### 下载图片 ###
```java
ImageLoaderHelper.getInstance().downloadImage(Context context, String url, ImageOptions options);
```

### 其他方法 ###
```java
ImageLoaderHelper.getInstance().resumeRequests();

ImageLoaderHelper.getInstance().pauseRequests();

ImageLoaderHelper.getInstance().cancleRequests(Object object);

ImageLoaderHelper.getInstance().onLowMemory();

ImageLoaderHelper.getInstance().onTrimMemory(int level);

ImageLoaderHelper.getInstance().clearMemoryCache();

ImageLoaderHelper.getInstance().clearDiskCache();
```

