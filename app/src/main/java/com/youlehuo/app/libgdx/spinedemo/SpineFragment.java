package com.youlehuo.app.libgdx.spinedemo;

import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

/**
 * Created by dayima on 17-6-14.
 */

public class SpineFragment extends AndroidFragmentApplication {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
//        cfg.r = cfg.g = cfg.b = cfg.a = 8;
//        View view = initializeForView(new SpineListener(), cfg);
//        if (view instanceof SurfaceView) {
//            GLSurfaceView glView = (GLSurfaceView) graphics.getView();
//            glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
//            glView.setZOrderMediaOverlay(true);
//            glView.setZOrderOnTop(true);
//        }
//        setContentView(view);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.r = cfg.g = cfg.b = cfg.a = 8;
        View view = initializeForView(new SpineListener(), cfg);
        if (view instanceof SurfaceView) {
            GLSurfaceView glView = (GLSurfaceView) graphics.getView();
            glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
            glView.setZOrderMediaOverlay(true);
            glView.setZOrderOnTop(true);
        }
        return view;
    }
}
