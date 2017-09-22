package com.youlehuo.app.demoforandroid.userui;

import android.view.View;
import android.widget.ImageView;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;
import com.youlehuo.app.demoforandroid.orther.DrawableDragShadowBuilder;

/**
 * Created by xiaohe on 17-9-22.
 * 拖放视图
 */

public class DragViewActivity extends BaseActivity implements View.OnLongClickListener {
    @Override
    protected int setView() {
        return R.layout.dragviewactivity;
    }

    @Override
    protected void initView() {
        findViewById(R.id.image1).setOnLongClickListener(this);
        findViewById(R.id.image2).setOnLongClickListener(this);
        findViewById(R.id.image3).setOnLongClickListener(this);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    public boolean onLongClick(View v) {
        DrawableDragShadowBuilder dragShadowBuilder = new DrawableDragShadowBuilder(v,context.getResources().getDrawable(R.drawable.img001));
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
        //开始一个拖动,将 View 的图片作为本地状态传递出去
        v.startDrag(null, dragShadowBuilder, ((ImageView) v).getDrawable(), 0);
//        v.startDragAndDrop(null,shadowBuilder,((ImageView) v).getDrawable(),0);
        return true;
    }
}
