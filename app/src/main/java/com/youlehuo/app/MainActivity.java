package com.youlehuo.app;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.youlehuo.app.aboutview.gaosimohu.VagueActivity;
import com.youlehuo.app.aboutview.myselfview.CarActivity;
import com.youlehuo.app.aboutview.notification.NotificationActivity;
import com.youlehuo.app.aboutview.paint_canvas.CanvasActivity;
import com.youlehuo.app.aboutview.paint_canvas.PathActivity;
import com.youlehuo.app.aboutview.pobupwindow.PobActivity;
import com.youlehuo.app.aboutview.recycleview.citylist.CityActivity;
import com.youlehuo.app.aboutview.recycleview.skidview.SkidActivity;
import com.youlehuo.app.aboutview.scrollview.SecondActivity;
import com.youlehuo.app.aboutview.viewpager.ViewPagerActivity;
import com.youlehuo.app.four_zujian.camera.CameraActivity;
import com.youlehuo.app.four_zujian.deeplinks.DeepLinksActivity;
import com.youlehuo.app.four_zujian.getapk.GetApkActivity;
import com.youlehuo.app.four_zujian.handlermessage.MessageActivity;
import com.youlehuo.app.four_zujian.intentaction.OpenIntentActivity;
import com.youlehuo.app.four_zujian.permission.PermissionActivity;
import com.youlehuo.app.four_zujian.service.ServiceActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity{
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
        adapter = new ActivityAdapter();
        lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,list.get(position).getClassName());
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
        list.add(initBean("通知栏兼容处理",NotificationActivity.class));
        list.add(initBean("Intent隐式意图", OpenIntentActivity.class));
        list.add(initBean("高斯模糊", VagueActivity.class));
        list.add(initBean("Service服务",ServiceActivity.class));
        list.add(initBean("城市列表右侧选中效果",CityActivity.class));
        list.add(initBean("侧滑删除", SkidActivity.class));
        list.add(initBean("图形绘画",CanvasActivity.class));
        list.add(initBean("6.0权限管理",PermissionActivity.class));
        list.add(initBean("Path图形绘制",PathActivity.class));
        list.add(initBean("相机调用",CameraActivity.class));
        list.add(initBean("viewpager",ViewPagerActivity.class));
        list.add(initBean("tip提示",PobActivity.class));

        lv_main.setAdapter(adapter);
    }

    private ClassBean initBean(String name,Class className){
        ClassBean classBean = new ClassBean();
        classBean.setName(name);
        classBean.setClassName(className);
        return classBean;
    }
    private class ActivityAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null){
                holder = new ViewHolder();
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_main,null);
                holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_name.setText(list.get(position).getName());
            return convertView;
        }
    }
    
    private class ViewHolder{
        TextView tv_name;
    }


    private class ClassBean{
        String name;
        Class className;

        public Class getClassName() {
            return className;
        }

        public void setClassName(Class clasName) {
            this.className = clasName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
