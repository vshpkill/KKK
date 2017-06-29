package com.youlehuo.app.libgdx.study;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by dayima on 17-6-9.
 */

public class Progress implements ApplicationListener {
    ProgressBar bar;
    Stage stage;
    AnimalActor animal;
    AssetManager manager;
    boolean hasini;
    @Override
    public void create() {
        // TODO Auto-generated method stub
        bar=new ProgressBar(0,0);
        //新建一个舞台
        stage=new Stage();//Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), true
        stage.addActor(bar);

        //记得初始化一下AssetManager实例
        manager=new AssetManager();
        //传入AssetManger的引用，便于animal的资源初始化，但是注意了，只有在调用iniResourse()后资源才被初始化
        animal=new AnimalActor(manager);
        //把资源加入载入列表,这里我放了一个29帧的动画，在asset文件夹下animal下有29张图片
        for(int i=1;i<30;i++){
            manager.load("animal/"+i+".png", Texture.class);
        }
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        bar.dispose();
        animal.dispose();
        manager.clear();
        manager.dispose();
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1f,1f,1f,0f);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        if(!manager.update()){
            bar.setProgress(manager.getProgress()*100);
        }
        //加载完成且之前没有初始化过AnimalActor，且在手触摸屏幕时初始化AnimalActor,并将进度条从舞台中移除，并加入AnimalActor对象
        if(!hasini&&manager.update()&&Gdx.input.isTouched()){
//            stage.removeActor(bar);
            animal.iniResource();
            stage.addActor(animal);
            hasini=true;
        }
        //我们做一个标记，看看未加载（Queued）完成的资源和已载入完成的资源的数量（Loaded）
        if(!manager.update()){
            System.out.println("QueuedAssets:"+manager.getQueuedAssets());
            System.out.println("LoadedAssets:"+manager.getLoadedAssets());
            System.out.println("Progress:"+manager.getProgress());
        }
    }

    @Override
    public void resize(int arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

}
