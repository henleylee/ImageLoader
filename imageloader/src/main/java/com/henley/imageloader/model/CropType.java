package com.henley.imageloader.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 裁剪类型
 *
 * @author Henley
 * @date 2017/3/31 9:44
 */
@IntDef({
        CropType.CENTER_CROP,
        CropType.CENTER_INSIDE,
        CropType.FIT_CENTER
})
@Retention(RetentionPolicy.SOURCE)
public @interface CropType {

    /**
     * 中间裁剪
     */
    int CENTER_CROP = 0;
    /**
     * 中间裁剪
     */
    int CENTER_INSIDE = 1;
    /**
     * 适合居中
     */
    int FIT_CENTER = 2;

}
