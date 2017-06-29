package me.kaohongshu.articles.kotlin.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import me.kaohongshu.articles.kotlin.App
import me.kaohongshu.articles.kotlin.di.qualifier.ForApp
import javax.inject.Named
import javax.inject.Singleton

/**
 * Author: shichunxiang
 * Date: 2017/6/28 0028
 * Des:
 */
@Module
class AppModule(val app: Context) {
    @Provides
    @Singleton
    @ForApp
    fun provideContext() = app
}