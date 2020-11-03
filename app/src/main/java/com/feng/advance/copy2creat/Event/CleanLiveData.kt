package com.feng.advance.copy2creat.Event

import android.arch.lifecycle.ExtLiveData
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.orhanobut.logger.Logger

class CleanLiveData<T> : ExtLiveData<T>() {
    //    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
//        super.observe(owner, object : Observer<T> {
//            override fun onChanged(t: T?) {
//                if (t != null) {
//                    observer.onChanged(t)
//                }
//            }
//        })
//    }
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        super.observe(owner, ObserWapper(observer,version >- 1))
    }

    class ObserWapper<T>(var origin: Observer<T>, var stop: Boolean = true) : Observer<T> {
        override fun onChanged(t: T?) {
            if (stop) {
                stop = false
            } else {
                origin.onChanged(t)
            }
        }
    }

//    override fun removeObserver(observer: Observer<in T>) {
//        super.removeObserver(observer)
//        Logger.w("remove")
//        value = null
//    }
//
//    override fun removeObservers(owner: LifecycleOwner) {
//        super.removeObservers(owner)
//        Logger.e("remove")
//    }

}