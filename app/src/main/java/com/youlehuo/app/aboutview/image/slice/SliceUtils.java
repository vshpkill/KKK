package com.youlehuo.app.aboutview.image.slice;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dayima on 17-5-10.
 */

public class SliceUtils {
    public static List<SliceBean> splitPic(Bitmap bitmap,int slices,boolean shuff){
        List<SliceBean> beanList = new ArrayList<>();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int slicew = width/slices;
        int sliceh = height/slices;
        for (int i = 0;i<slices;i++){
            for (int j = 0;j<slices;j++){
                int index = i*slices+j;
                int x = j*slicew;
                int y = i*sliceh;
                Bitmap sliceBitmap = Bitmap.createBitmap(bitmap,x,y,slicew,sliceh);
                SliceBean sliceBean = new SliceBean();
                sliceBean.setIndex(index);
                sliceBean.setBitmap(sliceBitmap);
                beanList.add(sliceBean);
            }
        }
        if (shuff){
            shuffle(beanList);
        }
        return beanList;
    }
    private static void shuffle(List<SliceBean> beanList){
        Collections.shuffle(beanList);
    }
}
