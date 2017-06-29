package me.kaohongshu.articles.kotlin.data.api

import io.reactivex.Observable
import me.kaohongshu.articles.kotlin.data.bean.Article
import me.kaohongshu.articles.kotlin.data.result.JsonResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Author: shichunxiang
 * Date: 2017/6/28 0028
 * Des:
 */
interface ArticleApi {
    @GET("getArticles")
    fun getArticles(@Query("page") page:Int):Observable<JsonResult<List<Article>>>
}