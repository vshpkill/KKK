package com.youlehuo.app.aboutview.recycleview.citylist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;

import com.youlehuo.app.R;

import java.util.List;

/**
 * Created by dayima on 17-1-12.
 * decoration 的 onDraw，child view 的 onDraw，decoration 的 onDrawOver，这三者是依次发生的
 */

public class CityItemDecoration extends RecyclerView.ItemDecoration {
    private Paint paint;
    private final TextPaint textPaint;
    private List<CityBean> nameList;
    private int decorationHeight = 90;

    public CityItemDecoration(Context context, List<CityBean> nameList) {
        this.nameList = nameList;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(context.getResources().getColor(R.color.colorPrimary));

        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(context.getResources().getColor(R.color.white));
        textPaint.setTextSize(50);
    }

    /**
     * getItemOffsets 中为 outRect 设置的4个方向的值，将被计算进所有 decoration 的尺寸中，
     * 而这个尺寸，被计入了 RecyclerView 每个 item view 的 padding 中
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (isFirstPinYin(position)) {
            outRect.top = decorationHeight;
        } else {
            outRect.top = 0;
        }
    }

    /**
     * 在 onDraw 为 divider 设置绘制范围，并绘制到 canvas 上，
     * 而这个绘制范围可以超出在 getItemOffsets 中设置的范围，
     * 但由于 decoration 是绘制在 child view 的底下，所以并不可见，但是会存在 overdraw
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        //绘制各个小组标题
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            String textValue = nameList.get(position).getCityFist();
            if (isFirstPinYin(position)) {
                int top = view.getTop() - decorationHeight;
                int bottom = view.getTop();
                c.drawRect(left, top, right, bottom, paint);
                c.drawText(textValue, left + 50, bottom - 25, textPaint);
            }
        }
    }

    /**
     * onDrawOver 是绘制在最上层的，所以它的绘制位置并不受限制
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int position = ((LinearLayoutManager)(parent.getLayoutManager())).findFirstVisibleItemPosition();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth()-parent.getPaddingRight();
        //绘制顶部固定显示条
        c.drawRect(left,0,right,decorationHeight,paint);
        String textValue = nameList.get(position).getCityFist();
        c.drawText(textValue,left+50,decorationHeight-25,textPaint);
    }

    /**
     * 判断是否需要加入分割
     * @param position
     * @return
     */
    private boolean isFirstPinYin(int position) {
        boolean isFirst;
        if (position == 0) {
            isFirst = true;
        } else {
            if (TextUtils.equals(nameList.get(position).getCityFist(), nameList.get(position - 1).getCityFist())) {
                isFirst = false;
            } else {
                isFirst = true;
            }
        }
        return isFirst;
    }
}
