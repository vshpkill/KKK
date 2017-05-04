package com.youlehuo.app.four_zujian.getapk;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.youlehuo.app.R;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dayima on 16-12-26.
 */

public class GetApkActivity extends Activity {
    @BindView(R.id.but_send)
    Button but_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getapkactivity);
        ButterKnife.bind(this);

        final PackageManager packageManager = getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        but_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendApkToFriend("com.wandoujia.phoenix2.usbproxy",packageManager);
            }
        });
    }

    /**
     * 获取应用名称
     * @param packageName
     * @param packageManager
     * @return
     */
    public String getApplicationInfo(String packageName,PackageManager packageManager){
        String appname = null;
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName,0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        appname = (String) packageManager.getApplicationLabel(applicationInfo);
        return appname;
    }

    /**
     * 获取应用图标
     * @param packageName
     * @param packageManager
     * @return
     */
    public Drawable getAppIcon(String packageName,PackageManager packageManager){
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName,0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Drawable drawable = applicationInfo.loadIcon(packageManager);
        return drawable;
    }

    /**
     * 最后更新时间
     * @param packageInfo
     * @return
     */
    public long getAppLastUpdaTime(PackageInfo packageInfo){
//        packageInfo.firstInstallTime;
        return packageInfo.lastUpdateTime;
    }

    /**
     * 获取apk大小
     * @param packageName
     * @param packageManager
     * @return
     */
    public String getAppMemorySize(String packageName,PackageManager packageManager){
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName,0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String apkPath = applicationInfo.sourceDir;
        File file = new File(apkPath);
        long apkSize = file.length()/1024/1024;
        return apkSize+"MB";
    }

    /**
     * 发送apk分享
     * @param packageName
     * @param packageManager
     */
    public void sendApkToFriend(String packageName,PackageManager packageManager){
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName,0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        File file = new File(applicationInfo.sourceDir);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text");
        intent.putExtra(Intent.EXTRA_TEXT, Uri.fromFile(file));

    }

}
