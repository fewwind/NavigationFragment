package com.chaozhuo.parentmanager.test.arouter

import com.chaozhuo.login.LoginActivity

// login是一级路径
class ARouterGrouplogin : IRouterGroup {
    override fun loadGroup(map: MutableMap<String, RouteInfoBean>) {
        map["/login/ui"] = RouteInfoBean("/login/ui", LoginActivity::class.java, "login")
    }
}