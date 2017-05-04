package com.youlehuo.app.aboutview.gaosimohu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.enrique.stackblur.StackBlurManager;
import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import butterknife.BindView;

/**
 * Created by dayima on 17-1-9.
 */

public class VagueActivity extends BaseActivity {
    @BindView(R.id.img_vague)
    ImageView img_vague;
    @BindView(R.id.but_progress1)
    Button but_progress1;
    @BindView(R.id.but_progress2)
    Button but_progress2;
    @BindView(R.id.but_progress3)
    Button but_progress3;
    @Override
    protected int setView() {
        return R.layout.vagueactivity;
    }

    @Override
    protected void initView() {
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.demo);
        blur(bitmap,img_vague,70,1);//模糊处理取值范围0-254,值越大越模糊
        but_progress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blur(bitmap,img_vague,100,1);//模糊处理取值范围0-254,值越大越模糊
            }
        });
        but_progress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blur(bitmap,img_vague,10,2);//模糊处理取值范围0-254,值越大越模糊
            }
        });
        but_progress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blur(bitmap,img_vague,10,3);//模糊处理取值范围0-254,值越大越模糊
            }
        });

        bitmap.recycle();
    }

    @Override
    protected void initVariables() {

    }
    private void blur(Bitmap bkg, ImageView view, int radius,int tag) {
        StackBlurManager blurManager = new StackBlurManager(bkg);
        blurManager.process(radius);//使用一定的半径与下列线
        view.setImageBitmap(blurManager.returnBlurredImage());
//        switch (tag){
//            case 1:
//                blurManager.process(radius);//使用一定的半径与下列线
//                break;
//            case 2:
//                blurManager.processRenderScript(context,radius);
//                break;
//            case 3:
//                blurManager.processNatively(radius);
//                break;
//        }
//        view.setImageBitmap(blurManager.returnBlurredImage());
    }
}
