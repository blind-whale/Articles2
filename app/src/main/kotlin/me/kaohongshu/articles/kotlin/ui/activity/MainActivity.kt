package me.kaohongshu.articles.kotlin.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.internal.NavigationMenuView
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import me.kaohongshu.articles.kotlin.R
import me.kaohongshu.articles.kotlin.data.bean.Article
import me.kaohongshu.articles.kotlin.data.result.JsonResult
import me.kaohongshu.articles.kotlin.databinding.ActivityMainBinding
import me.kaohongshu.articles.kotlin.di.component.ArticleModule
import me.kaohongshu.articles.kotlin.extension.getAppComponent
import me.kaohongshu.articles.kotlin.mvp.mainActivity.MainActivityContract
import me.kaohongshu.articles.kotlin.mvp.mainActivity.MainActivityPresenter
import me.kaohongshu.articles.kotlin.ui.adapter.ArticlesAdapter
import me.kaohongshu.articles.kotlin.ui.base.BaseNetworkActivity
import javax.inject.Inject

class MainActivity : BaseNetworkActivity<ActivityMainBinding>(), MainActivityContract.View {

    @Inject
    lateinit var presenter: MainActivityPresenter
    val mList = ArrayList<Article>()
    lateinit var adapter: ArticlesAdapter
    var page = 1
    var isLoadingMore = false
    lateinit var linearLM: LinearLayoutManager
    override fun createViewDataBinding(): ActivityMainBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getAppComponent().plus(ArticleModule(this)).inject(this)

        initDrawerView()

        initRecyclerView()
        viewDataBinding.refreshLayout.isRefreshing = true
        presenter.getArticles(page)
    }

    private fun initRecyclerView() {
        adapter = ArticlesAdapter(mList)
        linearLM = LinearLayoutManager(this)
        viewDataBinding.recyclerView.layoutManager = linearLM
        viewDataBinding.recyclerView.adapter = adapter
        viewDataBinding.refreshLayout.setOnRefreshListener {
            if (!isLoadingMore) {
                page = 1
                presenter.getArticles(page)
            }
        }
        viewDataBinding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (linearLM.findLastVisibleItemPosition()== adapter . itemCount -1) {
                    if (viewDataBinding.refreshLayout.isRefreshing || isLoadingMore) {
                        return
                    }
                    isLoadingMore = true
                    page++
                    presenter.getArticles(page)

                }
            }
        })
    }

    private fun initDrawerView() {
        val toggle = ActionBarDrawerToggle(this, viewDataBinding.drawerLayout,
                viewDataBinding.toolbar,
                R.string.app_name, R.string.app_name)
        viewDataBinding.drawerLayout.addDrawerListener(toggle)

        // 去除侧边栏滚动条
        val menuView = viewDataBinding.navView.getChildAt(0) as NavigationMenuView
        menuView.isVerticalScrollBarEnabled = false
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unSubscribe()
    }

    override fun setArticles(result: JsonResult<List<Article>>?) {
        if (viewDataBinding.refreshLayout.isRefreshing) {
            viewDataBinding.refreshLayout.isRefreshing = false
        } else {
            isLoadingMore = false
        }
        if (result == null || result.status == 0) {
            if (page > 1) page--
            return
        }
        if (result.data == null || result.data.size == 0) return
        if (page == 1) {
            mList.clear()
        }
        mList.addAll(result.data)
        adapter.notifyDataSetChanged()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
