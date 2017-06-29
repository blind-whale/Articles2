package me.kaohongshu.articles.kotlin.mvp

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Author: shichunxiang
 * Date: 2017/6/29 0029
 * Des:
 */
abstract class BaseNetworkPresenter{
    var compositeDisposable = CompositeDisposable()

    fun addSubscription(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    fun unSubscribe(){
        if(compositeDisposable.size()>0){
            compositeDisposable.clear()
        }
    }

    fun onError(code:Int,info:String){}
    fun onError(error:Throwable){
        var code =-1
        var info = "网络连接异常!"
        when (error){

        }
        Log.e("okhttp",error.message)
        onError(code,info)
    }
}