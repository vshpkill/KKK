package com.kkk.utils.phone;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by dayima on 16-11-14.
 * 手机信息获取工具类
 */

public class PhoneInfoUtils {
    //权限：<uses-permission android:name="android.permission.READ_PHONE_STATE" />

    /**
     * 获取IMEI号
     * @param context
     * @return
     */
    public static String getIMEI(Context context){
        TelephonyManager mTm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        return mTm.getDeviceId();
    }

    /**
     * 获取IESI号
     * @param context
     * @return
     */
    public static String getIESI(Context context){
        TelephonyManager mTm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        return mTm.getSubscriberId();
    }
    /**
     * 获取手机号码(可能无法获取)
     * @param context
     * @return
     */
    public static String getPhoneNum(Context context){
        TelephonyManager mTm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        return mTm.getLine1Number();
    }
    /**
     * 获取手机型号
     * @return
     */
    public static String getPhoneModel(){
        return android.os.Build.MODEL;
    }

    /**
     * 获取sdk版本
     * @return
     */
    public static int getSDKVersion(){
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取系统版本号
     * @return
     */
    public static String getReleaseVersion(){
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机MAC地址
     * @param context
     * @return
     */
    public static String getMacAddress(Context context){
        String result = "";
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        result = wifiInfo.getMacAddress();
        Log.i(TAG, "macAdd:" + result);
        return result;
    }

    /**
     * 获取手机CPU信息
     * @return
     */
    public static String[] getCpuInfo(){
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = {"", ""};  //1-cpu型号  //2-cpu频率
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
        }
        Log.i(TAG, "cpuinfo:" + cpuInfo[0] + " " + cpuInfo[1]);
        return cpuInfo;
    }

    /**
     * 获取手机可用内存和总内存
     * @param context
     * @return
     */
    public static String[] getTotalMemory(Context context){
        String[] result = {"",""};  //1-total 2-avail
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        mActivityManager.getMemoryInfo(mi);
        long mTotalMem = 0;
        long mAvailMem = mi.availMem;
        String str1 = "/proc/meminfo";
        String str2;
        String[] arrayOfString;
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            mTotalMem = Integer.valueOf(arrayOfString[1]).intValue() * 1024;
            localBufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        result[0] = Formatter.formatFileSize(context, mTotalMem);
        result[1] = Formatter.formatFileSize(context, mAvailMem);
        Log.i(TAG, "meminfo total:" + result[0] + " used:" + result[1]);
        return result;
    }

    /**
     * 获取手机安装的应用信息
     * @param context
     * @return
     */
    public static List<String> getAllApp(Context context){
        PackageManager pm = context.getPackageManager();
        //创建要返回的集合对象
        List<String> appInfos = new ArrayList<>();
        //获取手机中所有安装的应用集合
        List<ApplicationInfo> applicationInfos = pm.getInstalledApplications(PackageManager.MATCH_UNINSTALLED_PACKAGES);
        //遍历所有的应用集合
        for(ApplicationInfo info : applicationInfos){
            //获取应用程序的图标
            Drawable app_icon = info.loadIcon(pm);
            //获取应用的名称
            String app_name = info.loadLabel(pm).toString();
            //获取应用的包名
            String packageName = info.packageName;
            try {
                //获取应用的版本号
                PackageInfo packageInfo = pm.getPackageInfo(packageName, 0);
                String app_version = packageInfo.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            //判断应用程序是否是用户程序
            boolean isUserApp = filterApp(info);
            appInfos.add(app_name);
        }
        return appInfos;
    }

    /**
     * 判断应用程序是否是用户程序
     * @param info
     * @return
     */
    public static boolean filterApp(ApplicationInfo info) {
        //原来是系统应用，用户手动升级
        if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
            return true;
            //用户自己安装的应用程序
        } else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
            return true;
        }
        return false;
    }

    /**
     * DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
     * float density = displayMetrics.density; //屏幕密度
     * int densityDpi = displayMetrics.densityDpi;//屏幕密度dpi
     * int heightPixels = displayMetrics.heightPixels;//屏幕高度的像素
     * int widthPixels = displayMetrics.widthPixels;//屏幕宽度的像素
     * float scaledDensity = displayMetrics.scaledDensity;//字体的放大系数
     * float xdpi = displayMetrics.xdpi;//宽度方向上的dpi
     * float ydpi = displayMetrics.ydpi;//高度方向上的dpi
     * 获取屏幕宽
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕高
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    /**
     * dp转px
     * @param dpValue
     * @param context
     * @return
     */
    public static int dip2px(float dpValue,Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int dip2px(Double dpValue,Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue,Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
