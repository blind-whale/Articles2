package me.kaohongshu.articles.kotlin.util

import android.content.Context
import android.net.ConnectivityManager
import me.kaohongshu.articles.kotlin.App
import me.kaohongshu.articles.kotlin.extension.sendLocalBroadcast
import kotlin.properties.Delegates

/**
 * Author: shichunxiang
 * Date: 2017/6/28 0028
 * Des:
 */
object NetUtils{
    val ACTION_NETWORK_DISCONNECTED="me.kaohongshu.article.kotlin.util.action.network.disconnected"
    val ACTION_NETWORK_CONNECTED="me.kaohongshu.article.kotlin.util.action.network.disconnected"
    var isConnected:Boolean by Delegates.observable(true) {
        property, oldValue, newValue ->
        if (newValue) {
            App.instance.sendLocalBroadcast(ACTION_NETWORK_CONNECTED)
        } else {
            App.instance.sendLocalBroadcast(ACTION_NETWORK_DISCONNECTED)
        }
    }

    fun isNetworkConnected():Boolean{
        val cm= App.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        isConnected =cm.activeNetworkInfo?.isConnected?:false
        return isConnected
    }

}