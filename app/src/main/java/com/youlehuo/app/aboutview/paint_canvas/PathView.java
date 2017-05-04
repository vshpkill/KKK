package com.youlehuo.app.aboutview.paint_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dayima on 17-2-4.
 */

public class PathView extends View {

    private Paint paint;
    private Path path;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xffeeff00);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        path = new Path();
    }

    /**
     * moveTo：设置路径起始点
     * lineTo：添加直线到路径
     * arcTo：添加弧线到路径
     * rMoveTo：设置路径起始点，参数相对于当前绘制点
     * rLineTo：添加直线到路径，参数相对于当前绘制点
     * rArcTo：添加弧线到路径，参数相对于当前绘制点
     * close：闭合路径
     * addArc：添加一个圆弧到路径
     * addCircle：添加一个圆到路径
     * addOval：添加一个椭圆到路径
     * addRect：添加一个矩形到路径
     * reset：重置路径
     * offset：对路径进行偏移
     * op：两个路径组合操作
     * Path.Op有如下几种参数：
     * Path.Op.DIFFERENCE：减去Path2后Path1剩下的部分
     * Path.Op.INTERSECT：保留Path1与Path2共同的部分
     * Path.Op.REVERSE_DIFFERENCE：减去Path1后Path2剩下的部分
     * Path.Op.UNION：保留全部Path1和Path2
     * Path.Op.XOR：包含Path1与Path2但不包括两者相交的部分
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.moveTo(200, 200);
        path.lineTo(100, 400);
        path.lineTo(300, 400);
        path.close();
        canvas.drawPath(path, paint);

        path.moveTo(100, 800);
        path.lineTo(200, 500);
        path.lineTo(300, 800);
        path.lineTo(400, 500);
        path.lineTo(500, 800);
        canvas.drawPath(path, paint);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            path.arcTo(100, 900, 800, 1500, 0, 270, true);
            path.arcTo(100, 1600, 800, 2000, 90, 180, false);
            canvas.drawPath(path, paint);
        }
    }
}
