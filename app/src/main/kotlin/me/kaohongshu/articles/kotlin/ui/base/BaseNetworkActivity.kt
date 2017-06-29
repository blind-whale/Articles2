package me.kaohongshu.articles.kotlin.ui.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.os.PersistableBundle
import me.kaohongshu.articles.kotlin.util.NetUtils
import me.kaohongshu.articles.kotlin.extension.registerLocalReceiver
import me.kaohongshu.articles.kotlin.extension.unregisterLocalReceiver
import org.jetbrains.anko.toast

/**
 * Author: shichunxiang
 * Date: 2017/6/28 0028
 * Des:
 */
abstract class BaseNetworkActivity<T:ViewDataBinding>:BaseActivity<T>(){
    val receiver=object : BroadcastReceiver(){
        override fun onReceive(p0: Context?, intent: Intent?) {
            if(NetUtils.ACTION_NETWORK_CONNECTED.equals(intent?.action)){
                toast("网络已连接")
            }else if(NetUtils.ACTION_NETWORK_DISCONNECTED.equals(intent?.action)){
                toast("请检查网络连接设置")
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        val filter=IntentFilter()
        filter.addAction(NetUtils.ACTION_NETWORK_DISCONNECTED)
        filter.addAction(NetUtils.ACTION_NETWORK_CONNECTED)
        registerLocalReceiver(receiver,filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterLocalReceiver(receiver)
    }


}