package com.chaozhuo.parentmanager.test.kotlin;

public class SamTest {
    public void setSamType(ISAM1 isam1) {
    }

    public void setSamType(ISAM2 isam2) {
    }

    public interface ISAM1 {
        void getType(int type);
    }

    public interface ISAM2 {
        void getType(int type);
    }
}
