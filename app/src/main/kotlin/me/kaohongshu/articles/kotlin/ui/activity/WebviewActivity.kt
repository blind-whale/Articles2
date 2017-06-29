package me.kaohongshu.articles.kotlin.ui.activity

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import me.kaohongshu.articles.kotlin.R
import me.kaohongshu.articles.kotlin.databinding.ActivityWebviewBinding
import me.kaohongshu.articles.kotlin.ui.base.BaseActivity

class WebviewActivity : BaseActivity<ActivityWebviewBinding>() {
    override fun createViewDataBinding(): ActivityWebviewBinding {
        return DataBindingUtil.setContentView(this,R.layout.activity_webview)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
