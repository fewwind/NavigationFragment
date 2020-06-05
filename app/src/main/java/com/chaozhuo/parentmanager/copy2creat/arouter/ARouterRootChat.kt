package com.chaozhuo.parentmanager.copy2creat.arouter

class ARouterRootChat : IRouterRoot {
    override fun loadGroup(map: MutableMap<String, Class<out IRouterGroup>>) {
        map.put("Chat", ARouterGrouplogin::class.java)
    }
}