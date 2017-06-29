package me.kaohongshu.articles.kotlin.di.module

import dagger.Module
import dagger.Provides
import me.kaohongshu.articles.kotlin.data.api.ArticleApi
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Author: shichunxiang
 * Date: 2017/6/28 0028
 * Des:
 */
@Module
class ApiModule {
    @Provides @Singleton
    fun provideArticleApi(retrofit:Retrofit):ArticleApi{
        return retrofit.create(ArticleApi::class.java)
    }
}