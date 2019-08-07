package com.chaozhuo.parentmanager.test;

public enum EnumClass {
    One {
        @Override
        void test() {

        }
    },
    Two {
        @Override
        void test() {
        }
    };

    abstract void test();
}
