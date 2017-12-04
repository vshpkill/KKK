package com.youlehuo.app.simpleclass;

/**
 * Created by xiaohe on 17-11-27.
 * 一般形式DLC
 */

public class SimpleClassSafe {
    //volatile保证变量每次都从内存中读取
    private volatile static SimpleClassSafe simpleClassSafe = null;

    private SimpleClassSafe() {

    }

    public static SimpleClassSafe getSimpleClassSafe() {
        if (simpleClassSafe == null) {
            synchronized (SimpleClassSafe.class) {
                if (simpleClassSafe == null) {
                    simpleClassSafe = new SimpleClassSafe();
                }
            }
        }
        return simpleClassSafe;
    }
}
