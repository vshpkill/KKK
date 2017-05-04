package com.youlehuo.app.aboutview.myselfview;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.exceptions.InvalidDataException;
import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import butterknife.BindView;

/**
 * Created by dayima on 17-1-4.
 */

public class CarActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.cv_my)
    CarView cv_my;
    @BindView(R.id.but_add)
    Button but_add;
    @BindView(R.id.but_stop)
    Button but_stop;
    @BindView(R.id.but_reduce)
    Button but_reduce;

    /**
     * view的几个方法:
     * getTop：获取到的，是view自身的顶边到其父布局顶边的距离
     * getLeft：获取到的，是view自身的左边到其父布局左边的距离
     * getRight：获取到的，是view自身的右边到其父布局左边的距离
     * getBottom：获取到的，是view自身的底边到其父布局顶边的距离
     *
     * motionEvent的方法：
     * getX()：获取点击事件相对控件左边的x轴坐标，即点击事件距离控件左边的距离
     * getY()：获取点击事件相对控件顶边的y轴坐标，即点击事件距离控件顶边的距离
     * getRawX()：获取点击事件相对整个屏幕左边的x轴坐标，即点击事件距离整个屏幕左边的距离
     * getRawY()：获取点击事件相对整个屏幕顶边的y轴坐标，即点击事件距离整个屏幕顶边的距离
     */
    @Override
    protected int setView() {
        return R.layout.caractivity;
    }

    @Override
    protected void initView() {
        but_add.setOnClickListener(this);
        but_stop.setOnClickListener(this);
        but_reduce.setOnClickListener(this);
        but_add.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                speed += 5;
                if (speed >= 235) {
                    speed = 235;
                }
                SystemClock.sleep(20);
                cv_my.setValue(speed);
                cv_my.invalidate();
                return false;
            }
        });
        but_reduce.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                speed -= 5;
                if (speed < 0) {
                    speed = 0;
                }
                SystemClock.sleep(20);
                cv_my.setValue(speed);
                cv_my.invalidate();
                return false;
            }
        });
    }

    @Override
    protected void initVariables() {
// 在用户结账付款时...
        try {
            JSONArray orderList = new JSONArray();
            orderList.put("Apple iPhone6s");
            orderList.put("Nexus 6");

            JSONObject properties = new JSONObject();
            properties.put("OrderPaid", 50.10);                     // 订单支付金额
            properties.put("OrderList", orderList);                 // 产品列表
            properties.put("OrderDate", new Date());                // 订单时间

            // 记录浏览商品事件，并将商品ID、商品类别和是否被添加进收藏夹作为事件属性上传
            SensorsDataAPI.sharedInstance(this).track("PaidOrder", properties);
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    int speed;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_add:
                but_add.offsetTopAndBottom(speed);
                speed += 5;
                if (speed > 360) {
                    speed = 360;
                }
                cv_my.setValue(speed);
                break;
            case R.id.but_stop:
                speed = 0;
                cv_my.setValue(speed);
                cv_my.invalidate();
                break;
            case R.id.but_reduce:
                but_stop.offsetTopAndBottom(-speed);
                speed -= 5;
                if (speed < 0) {
                    speed = 0;
                }
                cv_my.setValue(speed);
                break;
        }
    }

    //测量view的几种方式
    private void getMeasured() {
    //一
        cv_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv_my.getMeasuredWidth();
                cv_my.getMeasuredHeight();
            }
        });
        //五
        cv_my.measure(0,0);
    }
    //二
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            cv_my.getMeasuredWidth();
            cv_my.getMeasuredHeight();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        //三
        cv_my.post(new Runnable() {
            @Override
            public void run() {
                cv_my.getMeasuredWidth();
                cv_my.getMeasuredHeight();
            }
        });
        //四
        cv_my.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cv_my.getMeasuredWidth();
                cv_my.getMeasuredHeight();
                cv_my.removeOnLayoutChangeListener(null);
            }
        });
    }
}
