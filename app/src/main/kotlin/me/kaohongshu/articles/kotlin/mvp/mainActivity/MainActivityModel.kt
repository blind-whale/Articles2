package me.kaohongshu.articles.kotlin.mvp.mainActivity

import io.reactivex.Observable
import me.kaohongshu.articles.kotlin.data.api.ArticleApi
import me.kaohongshu.articles.kotlin.data.bean.Article
import me.kaohongshu.articles.kotlin.data.result.JsonResult
import javax.inject.Inject

/**
 * Author: shichunxiang
 * Date: 2017/6/29 0029
 * Des:
 */
class MainActivityModel @Inject constructor(val articleApi:ArticleApi):MainActivityContract.Model{
    override fun getArticles(page: Int): Observable<JsonResult<List<Article>>> {
        return articleApi.getArticles(page)
    }

}