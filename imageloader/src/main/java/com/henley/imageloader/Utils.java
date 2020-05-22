package com.henley.imageloader;

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;
import androidx.annotation.RequiresPermission;

/**
 * 工具类
 *
 * @author Henley
 * @since 2019/4/18 17:01
 */
public final class Utils {

    /**
     * 判断是否在主线程
     */
    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * 判断WiFi是否可用
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isWiFiAvailable(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI); // 得到与WiFi相关的网络信息
        if (networkInfo == null) {
            return false;
        }
        return networkInfo.isAvailable();// 判断网络是否可用

    }

}
