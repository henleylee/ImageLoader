package com.henley.imageloader.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 图片类型
 *
 * @author Henley
 * @date 2017/3/31 9:44
 */
@IntDef({
        ImageType.BITMAP,
        ImageType.DRAWABLE,
        ImageType.GIF,
        ImageType.FILE
})
@Retention(RetentionPolicy.SOURCE)
public @interface ImageType {

    /**
     * Bitmap
     */
    int BITMAP = 0;
    /**
     * Drawable
     */
    int DRAWABLE = 1;
    /**
     * Gif
     */
    int GIF = 2;
    /**
     * File
     */
    int FILE = 3;

}
