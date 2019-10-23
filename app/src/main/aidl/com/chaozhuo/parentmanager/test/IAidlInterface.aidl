// IAidlInterface.aidl
package com.chaozhuo.parentmanager.test;

// Declare any non-default types here with import statements

interface IAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int callBinderMethod(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
