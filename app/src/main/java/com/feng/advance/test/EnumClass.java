package com.feng.advance.test;

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
