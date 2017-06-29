package me.kaohongshu.articles.kotlin.ui.base

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import me.kaohongshu.articles.kotlin.R
import org.jetbrains.anko.find
import org.jetbrains.anko.findOptional


/**
 * Author: shichunxiang
 * Date: 2017/6/28 0028
 * Des:
 */
abstract class BaseActivity<T:ViewDataBinding>:AppCompatActivity(){
    lateinit var viewDataBinding:T
    var toolbar: Toolbar?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding=createViewDataBinding()

        initToolbar()
    }

    open fun initToolbar() {

        toolbar=viewDataBinding.root.findOptional(R.id.toolbar)
        toolbar?.title=""
        setSupportActionBar(toolbar)
    }

    abstract fun createViewDataBinding():T
}