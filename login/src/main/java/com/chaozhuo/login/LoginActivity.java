package com.chaozhuo.login;

import android.app.Activity;
import android.os.Bundle;

@com.chaozhuo.rounte_annotation.Route({"lib", "login"})
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
