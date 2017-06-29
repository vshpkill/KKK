package com.youlehuo.app.libgdx.spinedemo;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonMeshRenderer;

/**
 * Created by dayima on 17-6-14.
 */

public class SpineListener implements ApplicationListener {
    private SkeletonMeshRenderer render;
    private SkeletonData sData;
    private AnimationState state;
    private Skeleton skeleton;
    private PolygonSpriteBatch polygonBatch;
    @Override
    public void create() {
        render = new SkeletonMeshRenderer();
        // 获取纹理集合
        TextureAtlas tAtlas = new TextureAtlas(Gdx.files.internal("spineboy.atlas"));
        // 读取json信息
        SkeletonJson sJson = new SkeletonJson(tAtlas);
        sJson.setScale(1f);// 缩放，以后不可更改
        sData = sJson.readSkeletonData(Gdx.files.internal("spineboy.json"));
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
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
// 清屏
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // 动画控制器更新时间步
        state.update(Gdx.graphics.getDeltaTime());
        // 动画控制器控制骨骼动画
        state.apply(skeleton);
        // 骨骼逐级进行矩阵变换
        skeleton.updateWorldTransform();
        // 绘制
        polygonBatch.begin();
        render.draw(polygonBatch, skeleton);
        polygonBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
    }
}
