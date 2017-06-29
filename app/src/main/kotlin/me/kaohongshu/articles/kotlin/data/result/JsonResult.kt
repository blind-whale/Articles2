package me.kaohongshu.articles.kotlin.data.result

/**
 * Author: shichunxiang
 * Date: 2017/6/28 0028
 * Des:
 */
open class JsonResult<T>(val status:Int,val errorCode:Int,
                         val errorInfo:String?,val data:T)