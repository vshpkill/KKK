package com.youlehuo.app.simpleclass;

/**
 * Created by xiaohe on 17-11-27.
 * 推荐使用静态内部类方式
 */

public class SimpleStaticIn {
    private SimpleStaticIn() {

    }

    public static SimpleStaticIn getSimpleStaticIn() {
        return SimpleHolder.sInstance;
    }

    private static class SimpleHolder {
        private static final SimpleStaticIn sInstance = new SimpleStaticIn();
    }
}

