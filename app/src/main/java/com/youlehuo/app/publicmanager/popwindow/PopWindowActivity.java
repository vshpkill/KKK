package com.youlehuo.app.publicmanager.popwindow;

import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import butterknife.BindView;

/**
 * Created by xiaohe on 18-2-28.
 */

public class PopWindowActivity extends BaseActivity {
    @BindView(R.id.but_pop)
    Button but_pop;

    @Override
    protected int setView() {
        return R.layout.popwindowactivity;
    }

    @Override
    protected void initView() {
        but_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.layout_pop, null);
                PopupWindow popWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popWindow.setOutsideTouchable(true);
                popWindow.setBackgroundDrawable(new BitmapDrawable());
                popWindow.setAnimationStyle(R.style.showPopupAnimation);
                popWindow.showAtLocation(but_pop, Gravity.CENTER, 0, 0);
            }
        });
    }

    @Override
    protected void initVariables() {

    }
}
