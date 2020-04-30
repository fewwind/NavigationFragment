package com.chaozhuo.parentmanager.test.arouter

interface IRouterRoot {
    fun loadGroup(map: MutableMap<String, Class<out IRouterGroup>>)
}