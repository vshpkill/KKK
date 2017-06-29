package com.youlehuo.app.libgdx.study;

import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.SurfaceView;
import android.view.View;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.youlehuo.app.libgdx.spinedemo.SpineListener;

/**
 * Created by dayima on 17-6-9.
 */

public class HelloGameActivity extends AndroidApplication {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.gdx_layout);
//        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
//        configuration.hideStatusBar = true;
//        initialize(new SpineListener(),configuration);//第二个参数如果设为true，则在opengl 2.0可用的情况下会使用opengl 2.0。Progress FristGame


        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.r = 1;
        cfg.g = 1;
        cfg.b = 1;
        cfg.a = 1;
        View view = initializeForView(new SpineListener(), cfg);
        if (view instanceof SurfaceView) {
            GLSurfaceView glView = (GLSurfaceView) graphics.getView();
            glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
            glView.setZOrderMediaOverlay(true);
            glView.setZOrderOnTop(true);
        }
        setContentView(view);
    }
}
