// StrongService.aidl
package com.youlehuo.app;

// Declare any non-default types here with import statements

interface StrongService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);
            void stopService();
            void startService();
}
