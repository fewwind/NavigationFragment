package com.chaozhuo.parentmanager.test.kotlin

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

class KtPermission {
    @Volatile
    var fragment: Fragment? = null
    var tag = "tag"

    constructor(activity: AppCompatActivity) {
        fragment = getInstance(activity.supportFragmentManager)
    }

    fun getInstance(fragManger: FragmentManager) =
            fragment ?: synchronized(this) {
                fragment ?: if (fragManger.findFragmentByTag(tag) == null) {
                    Fragment().apply {
                        fragManger.beginTransaction().add(this, tag).commitNow()
                    }
                } else {
                    fragManger.findFragmentByTag("tag") as Fragment
                }
            }
}