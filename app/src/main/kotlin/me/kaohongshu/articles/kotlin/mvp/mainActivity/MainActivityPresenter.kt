package me.kaohongshu.articles.kotlin.mvp.mainActivity

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.internal.schedulers.NewThreadScheduler
import me.kaohongshu.articles.kotlin.data.bean.Article
import me.kaohongshu.articles.kotlin.mvp.BaseNetworkPresenter
import javax.inject.Inject
import javax.inject.Named

/**
 * Author: shichunxiang
 * Date: 2017/6/29 0029
 * Des:
 */
class MainActivityPresenter @Inject constructor(val model: MainActivityModel,
                                                           val view: MainActivityContract.View)
    : MainActivityContract.Presenter() {
    override fun getArticles(page: Int) {
        val disposable=model.getArticles(page)
                .subscribeOn(NewThreadScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ jsonResult ->
                    if (jsonResult.status != 1) {
                        onError(jsonResult.errorCode, jsonResult.errorInfo ?: "")
                    }
                    view.setArticles(jsonResult)
                }, { error ->
                    onError(error)
                    view.setArticles(null)
                })
        addSubscription(disposable)
    }
}