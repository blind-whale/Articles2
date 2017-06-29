package me.kaohongshu.articles.kotlin.di.component

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import me.kaohongshu.articles.kotlin.mvp.mainActivity.MainActivityContract
import me.kaohongshu.articles.kotlin.ui.activity.MainActivity

/**
 * Author: shichunxiang
 * Date: 2017/6/29 0029
 * Des:
 */
@Subcomponent(modules = arrayOf(ArticleModule::class))
interface MainActivityComponent {
    fun inject(activity: MainActivity)
}
@Module
class ArticleModule(val view: MainActivityContract.View){
    @Provides
    fun provideView()=view
}