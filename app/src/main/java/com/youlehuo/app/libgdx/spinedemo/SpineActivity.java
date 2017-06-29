package com.youlehuo.app.libgdx.spinedemo;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidFiles;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonMeshRenderer;

import javax.microedition.khronos.opengles.GL10;

import static com.badlogic.gdx.Gdx.graphics;

/**
 * Created by dayima on 17-6-14.
 */

public class SpineActivity extends Activity {

    private GLSurfaceView mGLView;
    protected AndroidFiles files;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new GLSurfaceView(this);
        mGLView.setRenderer(new ClearRenderer());
        files = new AndroidFiles(this.getAssets(), this.getFilesDir().getAbsolutePath());
        setContentView(mGLView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLView.onResume();
    }
    private SkeletonMeshRenderer render;
    private SkeletonData sData;
    private AnimationState state;
    private Skeleton skeleton;
    private PolygonSpriteBatch polygonBatch;

    class ClearRenderer implements GLSurfaceView.Renderer {

        @Override
        public void onSurfaceCreated(GL10 gl, javax.microedition.khronos.egl.EGLConfig config) {
            // Do nothing special.
            render = new SkeletonMeshRenderer();
            // 获取纹理集合
            TextureAtlas tAtlas = new TextureAtlas(files.internal("spineboy.atlas"));
            // 读取json信息
            SkeletonJson sJson = new SkeletonJson(tAtlas);
            sJson.setScale(1f);// 缩放，以后不可更改
            sData = sJson.readSkeletonData(files.internal("spineboy.json"));
            // 初始化动画信息
            AnimationStateData animData = new AnimationStateData(sData);
            state = new AnimationState(animData);
            // 初始化骨骼信息
            skeleton = new Skeleton(sData);
            // 初始化batch
            polygonBatch = new PolygonSpriteBatch();
            // 播放动画
            state.setAnimation(0, "walk", true);
            // 设置位置
            skeleton.setPosition(500, 200);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            gl.glViewport(0, 0, width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
            // 动画控制器更新时间步
            state.update(graphics.getDeltaTime());
            // 动画控制器控制骨骼动画
            state.apply(skeleton);
            // 骨骼逐级进行矩阵变换
            skeleton.updateWorldTransform();
            // 绘制
            polygonBatch.begin();
            render.draw(polygonBatch, skeleton);
            polygonBatch.end();
//            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        }
    }
}
