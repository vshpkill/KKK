package com.kkk.utils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by dayima on 16-12-14.
 */

public class NetStateUtils {
    /**
     * 网络不可用
     */
    public static final int NONETWORK = 0;
    /**
     * 是wifi连接
     */
    public static final int WIFI = 1;
    /**
     * 不是wifi连接
     */
    public static final int NOWIFI = 2;

    /**
     * 检查是否连接网络
     *
     * @param
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

    /**
     * 网络链接类型
     * @param context
     * @return
     */
    public static int getNetWorkType(Context context) {
        if (!isNetworkConnected(context)) {
            return NetStateUtils.NONETWORK;
        }
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting())
            return NetStateUtils.WIFI;
        else
            return NetStateUtils.NOWIFI;
    }
}
