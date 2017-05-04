package com.youlehuo.app.aboutview.scrollview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by dayima on 16-12-14.
 */

public class MyScrollView extends ScrollView {
    private scrollTopListener listener;
    public interface scrollTopListener {
        void scrollTop();
    }
    public void setListener(scrollTopListener listener) {
        this.listener = listener;
    }
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                int scrollY = getScrollY();
                if (scrollY==0){
                   getParent().requestDisallowInterceptTouchEvent(false);
                    if (listener!=null){
                        listener.scrollTop();
                    }
                }else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
}
