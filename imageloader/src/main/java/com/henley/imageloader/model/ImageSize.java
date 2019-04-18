package com.henley.imageloader.model;

/**
 * 图片尺寸(最终显示在ImageView上的宽高像素)
 *
 * @author Henley
 * @date 2017/3/31 9:43
 */
public class ImageSize {

    private final int width;
    private final int height;

    public ImageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isValidity() {
        return width > 0 && height > 0;
    }

}
