package com.youlehuo.app;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.youlehuo.app.zlibrary.ActivityAdapter;
import com.youlehuo.app.zlibrary.ClassBean;
import com.youlehuo.app.zlibrary.LibraryActivity;
import com.youlehuo.app.zlibrary.PublicActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity {
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
                intent.setClass(MainActivity.this, list.get(position).getClassName());
                startActivity(intent);
            }
        });
    }
    @Override
    protected void initVariables() {
        list.add(initBean("小功能实现", LibraryActivity.class));
        list.add(initBean("公众号功能", PublicActivity.class));
        lv_main.setAdapter(adapter);
    }

    private ClassBean initBean(String name, Class className) {
        ClassBean classBean = new ClassBean();
        classBean.setName(name);
        classBean.setClassName(className);
        return classBean;
    }
}
