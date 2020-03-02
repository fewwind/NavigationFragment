package com.chaozhuo.parentmanager.design;

/**
 * 枚举替换if else
 */
public enum CheckState {
    Success("success") {
        @Override
        void show() {

        }
    },
    Intercept("intercept") {
        @Override
        void show() {

        }
    },
    DoIng("doing") {
        @Override
        void show() {

        }
    };
    public String state;

    public static void hand(String name) {
        CheckState[] values = CheckState.values();
        for (int i = 0; i < values.length; i++) {
            CheckState value = values[i];
            if (value.state.equals(name)) {
                value.show();
                break;
            }
        }
    }

    CheckState(String state) {
        this.state = state;
    }

    abstract void show();
}
