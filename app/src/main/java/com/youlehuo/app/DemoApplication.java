package com.youlehuo.app;

import android.app.Application;

import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;

/**
 * Created by dayima on 17-3-1.
 */

public class DemoApplication extends Application {
    private static DemoApplication application;
    public static DemoApplication getApplication(){
        return application;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
//        initSC();
    }
    // 数据接收的 URL
    public final String SA_SERVER_URL = "http://dayimatest.cloud.sensorsdata.cn:8006/sa?project=default&token=da466f798d1c2c52";
    // 配置分发的 URL
    public final String SA_CONFIGURE_URL = "http://dayimatest.cloud.sensorsdata.cn:8006/config/?project=default";
    // Debug 模式选项
    //   SensorsDataAPI.DebugMode.DEBUG_OFF - 关闭 Debug 模式
    //   SensorsDataAPI.DebugMode.DEBUG_ONLY - 打开 Debug 模式，校验数据，但不进行数据导入
    //   SensorsDataAPI.DebugMode.DEBUG_AND_TRACK - 打开 Debug 模式，校验数据，并将数据导入到Sensors Analytics 中
    // 注意！请不要在正式发布的 App 中使用 Debug 模式！
    final SensorsDataAPI.DebugMode SA_DEBUG_MODE = SensorsDataAPI.DebugMode.DEBUG_AND_TRACK;

    private void initSC() {
        SensorsDataAPI sensorsDataAPI = SensorsDataAPI.sharedInstance(
                this,                               // 传入 Context
                SA_SERVER_URL,                      // 数据接收的 URL
                SA_CONFIGURE_URL,                   // 配置分发的 URL
                SA_DEBUG_MODE);                     // Debug 模式选项;

        // 打开自动采集
        sensorsDataAPI.enableAutoTrack();
        //允许可视化埋点
        sensorsDataAPI.enableEditingVTrack();
    }
}
