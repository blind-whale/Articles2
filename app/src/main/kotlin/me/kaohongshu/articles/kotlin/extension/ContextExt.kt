package me.kaohongshu.articles.kotlin.extension

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import me.kaohongshu.articles.kotlin.App
import me.kaohongshu.articles.kotlin.di.component.AppComponent

/**
 * Author: shichunxiang
 * Date: 2017/6/28 0028
 * Des:
 */
fun Context.sendLocalBroadcast(action:String){
    LocalBroadcastManager.getInstance(this)
            .sendBroadcast(Intent(action))
}

fun Context.registerLocalReceiver(receiver: BroadcastReceiver,filter: IntentFilter){
    LocalBroadcastManager.getInstance(this)
            .registerReceiver(receiver,filter)
}

fun Context.unregisterLocalReceiver(receiver: BroadcastReceiver){
    LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(receiver)
}

fun Context.getAppComponent():AppComponent= App.instance.appComponent
