package com.henley.imageloader.glide;


import android.util.Log;

import com.bumptech.glide.load.engine.executor.GlideExecutor;

/**
 * 处理池中出现的未被捕获异常的策略
 *
 * @author Henley
 * @date 2019/3/29 17:31
 */
public class GlideUncaughtThrowableStrategy implements GlideExecutor.UncaughtThrowableStrategy {

    private static final String TAG = "GlideExecutor";

    @Override
    public void handle(Throwable t) {
        if (t != null && Log.isLoggable(TAG, Log.ERROR)) {
            Log.e(TAG, "Request threw uncaught throwable", t);
        }
    }

}
