package com.youlehuo.app.simpleclass;

/**
 * Created by xiaohe on 17-11-27.
 * 懒汉式
 */

public class SimpleClassLazy {
    private static SimpleClassLazy classLazy = new SimpleClassLazy();

    private SimpleClassLazy(){

    }

    public static SimpleClassLazy getClassLazy(){
        return classLazy;
    }
}
