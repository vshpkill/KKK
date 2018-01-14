package com.youlehuo.app.zlibrary;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;
import com.youlehuo.app.UtilsActivity;
import com.youlehuo.app.aboutview.animation.AnimationActivity;
import com.youlehuo.app.aboutview.bottomtabview.BTabViewActivity;
import com.youlehuo.app.aboutview.flowlayout.FlowLayoutActivity;
import com.youlehuo.app.aboutview.gaosimohu.VagueActivity;
import com.youlehuo.app.aboutview.image.slice.SliceActivity;
import com.youlehuo.app.aboutview.littlefunction.LittleFActivity;
import com.youlehuo.app.aboutview.myselfview.CarActivity;
import com.youlehuo.app.aboutview.notification.NotificationActivity;
import com.youlehuo.app.aboutview.paint_canvas.CanvasActivity;
import com.youlehuo.app.aboutview.paint_canvas.PathActivity;
import com.youlehuo.app.aboutview.pobupwindow.PobActivity;
import com.youlehuo.app.aboutview.recycleview.citylist.CityActivity;
import com.youlehuo.app.aboutview.recycleview.skidview.SkidActivity;
import com.youlehuo.app.aboutview.scrollview.SecondActivity;
import com.youlehuo.app.aboutview.tablayout.TabLayoutActivity;
import com.youlehuo.app.aboutview.taobaogoods.TaoBaoActivity;
import com.youlehuo.app.aboutview.viewdoubleuse.DoubleUseOneActivity;
import com.youlehuo.app.aboutview.viewpager.ViewPagerActivity;
import com.youlehuo.app.demoforandroid.userui.DragViewActivity;
import com.youlehuo.app.four_zujian.camera.CameraActivity;
import com.youlehuo.app.four_zujian.deeplinks.DeepLinksActivity;
import com.youlehuo.app.four_zujian.getapk.GetApkActivity;
import com.youlehuo.app.four_zujian.handlermessage.MessageActivity;
import com.youlehuo.app.four_zujian.intentaction.OpenIntentActivity;
import com.youlehuo.app.four_zujian.permission.PermissionActivity;
import com.youlehuo.app.four_zujian.service.ServiceActivity;
import com.youlehuo.app.libgdx.spinedemo.SpineActivity;
import com.youlehuo.app.libgdx.study.HelloGameActivity;
import com.youlehuo.app.lottie.LottieActivity;
import com.youlehuo.app.videodemo.VideoListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xiaohe on 17-12-22.
 */

public class LibraryActivity extends BaseActivity {
    @BindView(R.id.lv_main)
    ListView lv_main;
    private ActivityAdapter adapter;
    private List<ClassBean> list = new ArrayList();

    @Override
    protected int setView() {
        return R.layout.activity_main;
    }
    @Override
    protected void initView() {
        adapter = new ActivityAdapter(context,list);
        lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(LibraryActivity.this, list.get(position).getClassName());
                startActivity(intent);
            }
        });
    }
    @Override
    protected void initVariables() {
        list.add(initBean("滑动冲突解决", SecondActivity.class));
        list.add(initBean("获取应用安装信息", GetApkActivity.class));
        list.add(initBean("DeepLinks", DeepLinksActivity.class));
        list.add(initBean("子线程使用Handler", MessageActivity.class));
        list.add(initBean("汽车速度表盘", CarActivity.class));
        list.add(initBean("通知栏兼容处理", NotificationActivity.class));
        list.add(initBean("Intent隐式意图", OpenIntentActivity.class));
        list.add(initBean("高斯模糊", VagueActivity.class));
        list.add(initBean("Service服务", ServiceActivity.class));
        list.add(initBean("城市列表右侧选中效果", CityActivity.class));
        list.add(initBean("侧滑删除", SkidActivity.class));
        list.add(initBean("图形绘画", CanvasActivity.class));
        list.add(initBean("6.0权限管理", PermissionActivity.class));
        list.add(initBean("Path图形绘制", PathActivity.class));
        list.add(initBean("相机调用", CameraActivity.class));
        list.add(initBean("viewpager", ViewPagerActivity.class));
        list.add(initBean("tip提示", PobActivity.class));
        list.add(initBean("切片view", SliceActivity.class));
        list.add(initBean("游戏引擎", HelloGameActivity.class));
        list.add(initBean("游戏测试", SpineActivity.class));
        list.add(initBean("lottie动画测试", LottieActivity.class));
        list.add(initBean("文件处理", UtilsActivity.class));
        list.add(initBean("动画处理", AnimationActivity.class));
        list.add(initBean("标签", FlowLayoutActivity.class));
        list.add(initBean("tab卡片", TabLayoutActivity.class));
        list.add(initBean("商品详情", TaoBaoActivity.class));
        list.add(initBean("创建拖放视图", DragViewActivity.class));
        list.add(initBean("小功能整理", LittleFActivity.class));
        list.add(initBean("底部导航View", BTabViewActivity.class));
        list.add(initBean("控件不同Activity使用测试", DoubleUseOneActivity.class));
        list.add(initBean("列表视频自动播放", VideoListActivity.class));
        lv_main.setAdapter(adapter);
    }
    private ClassBean initBean(String name, Class className) {
        ClassBean classBean = new ClassBean();
        classBean.setName(name);
        classBean.setClassName(className);
        return classBean;
    }
}
