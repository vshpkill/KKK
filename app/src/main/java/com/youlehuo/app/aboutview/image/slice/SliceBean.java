package com.youlehuo.app.aboutview.image.slice;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by dayima on 17-5-10.
 */

public class SliceBean implements Serializable {
    private int index;
    private Bitmap bitmap;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
