package me.kaohongshu.articles.kotlin.di.component

import dagger.Component
import me.kaohongshu.articles.kotlin.App
import me.kaohongshu.articles.kotlin.di.module.ApiModule
import me.kaohongshu.articles.kotlin.di.module.AppModule
import me.kaohongshu.articles.kotlin.di.module.RetrofitModule
import javax.inject.Singleton

/**
 * Author: shichunxiang
 * Date: 2017/6/28 0028
 * Des:
 */
@Component(modules = arrayOf(AppModule::class, ApiModule::class,RetrofitModule::class))
@Singleton
interface AppComponent{
    fun inject(app:App)
    fun plus(module:ArticleModule):MainActivityComponent
}