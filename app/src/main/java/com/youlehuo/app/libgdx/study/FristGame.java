package com.youlehuo.app.libgdx.study;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;


/**
 * Created by dayima on 17-6-9.
 */

public class FristGame implements ApplicationListener {
    SpriteBatch batch;
    BitmapFont bf;
    ParticleEffect particle;
    ParticleEffect tem;
    ParticleEffectPool particlepool;
    ArrayList<ParticleEffect> particlelist;


    @Override
    public void create() {
        batch=new SpriteBatch();
        bf=new BitmapFont();
        //初始化粒子变量
        particle = new ParticleEffect();
        particle.load(Gdx.files.internal("particle.p"), Gdx.files.internal(""));
        particlepool=new ParticleEffectPool(particle, 5, 10);
        particlelist=new ArrayList<ParticleEffect>();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0f,0f,0f,0f);
        batch.begin();
        bf.draw(batch, "Testin——Mkey libgdx(2)",Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        batch.end();

        if(true){
            if(Gdx.input.isTouched()){
                //当此触摸点与上一触摸点距离大于一定值的时候触发新的粒子系统，由此减小系统负担
                tem=particlepool.obtain();
                tem.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
                particlelist.add(tem);
            }
            batch.begin();
            for(int i=0;i<particlelist.size();i++){
                particlelist.get(i).draw(batch, Gdx.graphics.getDeltaTime());
            }
            batch.end();

            //清除已经播放完成的粒子系统
            ParticleEffect temparticle;
            for(int i=0;i<particlelist.size();i++){
                temparticle=particlelist.get(i);
                if(temparticle.isComplete()){
                    particlelist.remove(i);
                }
            }
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        bf.dispose();
        //千万别忘了释放内存
        particle.dispose();
        if(tem!=null)
            tem.dispose();
        particlepool.clear();
    }
}
