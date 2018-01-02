package com.youlehuo.app.zlibrary;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;
import com.youlehuo.app.publicmanager.ipc.IPCActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xiaohe on 17-12-22.
 */

public class PublicActivity extends BaseActivity {
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
        adapter = new ActivityAdapter(context, list);
        lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(PublicActivity.this, list.get(position).getClassName());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initVariables() {
        list.add(initBean("进程通信IPC", IPCActivity.class));
        lv_main.setAdapter(adapter);
    }

    private ClassBean initBean(String name, Class className) {
        ClassBean classBean = new ClassBean();
        classBean.setName(name);
        classBean.setClassName(className);
        return classBean;
    }
}
