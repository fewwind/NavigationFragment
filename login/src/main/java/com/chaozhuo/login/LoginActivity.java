package com.chaozhuo.login;

import android.app.Activity;
import android.os.Bundle;
import com.chaozhuo.rounte_annotation.MtRoute;

@MtRoute({ "login2" })
//@Route(path = "/login2/loginAc")
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
