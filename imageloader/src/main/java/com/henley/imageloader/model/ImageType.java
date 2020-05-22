package com.henley.imageloader.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 * 图片类型
 *
 * @author Henley
 * @since 2017/3/31 9:44
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
