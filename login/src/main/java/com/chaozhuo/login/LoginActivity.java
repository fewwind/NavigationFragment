package com.chaozhuo.login;

import android.app.Activity;
import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/login2/loginAc")
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
