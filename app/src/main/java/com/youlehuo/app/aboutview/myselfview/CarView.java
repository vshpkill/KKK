package com.youlehuo.app.aboutview.myselfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.kkk.utils.phone.PhoneInfoUtils;

/**
 * Created by dayima on 17-1-4.
 */

public class CarView extends View {

    private Paint paint;
    private Paint paint1;
    private Paint paint2;
    private Context context;
    private int radius;
    private int cx;
    private int cy;
    private TextPaint textPaint;
    private int baseX;
    private int baseY;
    private float textScale;
    private TextPaint textspeed;
    private int value;
    public CarView(Context context) {
        super(context);
        this.context = context;
        initPoint();
    }

    public CarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPoint();
    }

    public CarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initPoint();
    }

    private void initPoint() {
        paint = new Paint();
        paint.setAntiAlias(true);

        paint1 = new Paint();
        paint1.setAntiAlias(true);

        paint2 = new Paint();
        paint2.setAntiAlias(true);

        cx = PhoneInfoUtils.getScreenWidth(context) / 2;
        cy = PhoneInfoUtils.getScreenHeight(context) / 3;
        radius = PhoneInfoUtils.dip2px(150, context);
        textPaint = new TextPaint();
        textPaint.setTextSize(40);
        textPaint.setAntiAlias(true);
        textScale = Math.abs(textPaint.descent() + textPaint.ascent()) / 2;

        textspeed = new TextPaint();
        textspeed.setTextSize(80);
        textspeed.setColor(0xFFffffff);
        textspeed.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xFF343434);
        canvas.drawCircle(cx, cy, radius, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0xFF303F9F);
        paint.setStrokeWidth(10);
        canvas.drawCircle(cx, cy, radius, paint);

        paint.setStrokeWidth(10);
        canvas.drawCircle(cx, cy, radius - 30, paint);
        drawSpeedArea(canvas);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setColor(0xFFfe813f);
        canvas.drawCircle(cx, cy, radius - 240, paint);
        drawScale(canvas);
        drawCenterTextSpeed(canvas);
        Log.e("刷新界面","刷新刷新");
    }

    private void drawScale(Canvas canvas) {
        paint.setColor(0xFFFD671A);
        paint.setStrokeWidth(3);
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                canvas.drawLine(cx, cy + radius - 37, cx, cy + radius - 85, paint);
            } else {
                canvas.drawLine(cx, cy + radius - 37, cx, cy + radius - 60, paint);
            }
            canvas.rotate(6, cx, cy);
        }
        for (int i = 2; i < 11; i++) {
            drawText(canvas, i * 30);
        }
        drawCenterText(canvas);
    }

    private void drawText(Canvas canvas, int value) {
        String text = String.valueOf(value);
        textPaint.setColor(0xFFFF4081);
        // 计算Baseline绘制的起点X轴坐标
        baseX = (int) (cx + ((radius - 120) * Math.sin(-value * Math.PI / 180)) - textPaint.measureText(text) / 2f);
        // 计算Baseline绘制的Y坐标
        baseY = (int) ((cy + (radius - 120) * Math.cos(-value * Math.PI / 180)) + textScale);
        canvas.drawText(text, baseX, baseY, textPaint);
    }

    private void drawCenterText(Canvas canvas) {
        textPaint.setTextSize(40);
        textPaint.setColor(0xFFfa8072);
        int leanth = (int) textPaint.measureText("KM/H");
        int kmX = cx - leanth / 2;
        int kmY = cy + 100;
        canvas.drawText("KM/H", kmX, kmY, textPaint);
    }

    /**
     * 绘制速度区域扇形
     */
    public void drawSpeedArea(Canvas canvas) {
        RectF rectF = new RectF();
        rectF.set(cx-radius+35,cy-radius+35,cx+radius-35,cy+radius-35);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setColor(0x88e0ffff);
        canvas.drawArc(rectF,150,value,true,paint1);

        rectF.set(cx-210,cy-210,cx+210,cy+210);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(0xFF343434);
        canvas.drawArc(rectF,0,360,true,paint2);
    }

    public void drawCenterTextSpeed(Canvas canvas) {
        String text = String.valueOf(value);
        int leanth = (int) textspeed.measureText(text);
        int kmX = cx - leanth / 2;
        int kmY = (int) (cy + Math.abs(textspeed.descent() + textspeed.ascent()) / 2);
        canvas.drawText(text, kmX, kmY, textspeed);
    }

    public void setValue(int value){
        this.value = value;
    }

}
