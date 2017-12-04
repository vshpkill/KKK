package com.youlehuo.app.simpleclass;


/**
 * Created by xiaohe on 17-11-27.
 * 枚举单例推荐使用模式，线程安全
 */

public class SimpleEnum {
    private SimpleEnum() {
    }

    //获取
    public static SimpleEnum getSimpleEnum() {
        return SomeThing.INSTANCE.getInstance();
    }

    public enum SomeThing {
        INSTANCE;
        private SimpleEnum instance;

        SomeThing() {
            instance = new SimpleEnum();
        }

        public SimpleEnum getInstance() {
            return instance;
        }
    }


}
