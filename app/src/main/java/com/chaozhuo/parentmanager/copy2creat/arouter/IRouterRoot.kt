package com.chaozhuo.parentmanager.copy2creat.arouter

interface IRouterRoot {
    fun loadGroup(map: MutableMap<String, Class<out IRouterGroup>>)
}