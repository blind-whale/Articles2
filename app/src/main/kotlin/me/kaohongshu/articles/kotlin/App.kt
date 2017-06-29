package me.kaohongshu.articles.kotlin

import android.app.Application
import me.kaohongshu.articles.kotlin.di.component.AppComponent
import me.kaohongshu.articles.kotlin.di.component.DaggerAppComponent
import me.kaohongshu.articles.kotlin.di.module.ApiModule
import me.kaohongshu.articles.kotlin.di.module.AppModule
import me.kaohongshu.articles.kotlin.di.module.RetrofitModule

/**
 * Author: shichunxiang
 * Date: 2017/6/28 0028
 * Des:
 */
class App:Application(){
    companion object {
        lateinit var instance:App
    }

    lateinit var appComponent:AppComponent

    override fun onCreate() {
        super.onCreate()
        instance=this
        appComponent= DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .apiModule(ApiModule())
                .retrofitModule(RetrofitModule()).build()
    }
}