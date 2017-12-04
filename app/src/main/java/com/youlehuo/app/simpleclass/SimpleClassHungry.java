package com.youlehuo.app.simpleclass;

/**
 * Created by xiaohe on 17-11-27.
 * 饿汉式
 */

public class SimpleClassHungry {
    private static SimpleClassHungry classHungry;

    private SimpleClassHungry(){

    }

    public static SimpleClassHungry getClassHungry(){
        if (classHungry!=null){
            classHungry = new SimpleClassHungry();
        }
        return classHungry;
    }
}
