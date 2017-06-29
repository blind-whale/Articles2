package me.kaohongshu.articles.kotlin.mvp.mainActivity

import io.reactivex.Observable
import me.kaohongshu.articles.kotlin.data.bean.Article
import me.kaohongshu.articles.kotlin.data.result.JsonResult
import me.kaohongshu.articles.kotlin.mvp.BaseNetworkPresenter

/**
 * Author: shichunxiang
 * Date: 2017/6/29 0029
 * Des:
 */
interface MainActivityContract{
    interface Model{
        fun getArticles(page:Int): Observable<JsonResult<List<Article>>>
    }

    interface View{
        fun setArticles(result:JsonResult<List<Article>>?)
    }

    abstract class Presenter:BaseNetworkPresenter(){
       abstract fun getArticles(page:Int)
    }
}