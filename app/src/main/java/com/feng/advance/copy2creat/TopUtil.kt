package com.feng.advance.copy2creat

import com.feng.advance.copy2creat.myrxjava.FConsumer
import com.feng.advance.copy2creat.myrxjava.FObservable
import com.feng.advance.copy2creat.myrxjava.FObservableOnSubscribe
import com.feng.advance.copy2creat.myrxjava.FObserver
import com.orhanobut.logger.Logger
import io.reactivex.schedulers.Schedulers

object TopUtil {
    @JvmStatic
    fun rxJava() {
        FObservable.creat(object : FObservableOnSubscribe<String> {
            override fun subscribe(observer: FObserver<String>) {
                observer.onNext("myRxjava")
            }
        }).observeOn(Schedulers.io()).subscribe(object : FConsumer<String> {
            override fun onNext(t: String?) {
                Logger.v("FObserver next == > $t")
            }
        })
    }
    @JvmStatic
    fun print(any:Any?){
        println(any?.toString())
    }
}