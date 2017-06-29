package me.kaohongshu.articles.kotlin.data.bean

import com.google.gson.annotations.SerializedName

/**
 * Author: shichunxiang
 * Date: 2017/6/28 0028
 * Des:
 */
data class Article(
        @SerializedName("article_id")
        val articleId:String,
        val title:String,
        @SerializedName("created_at")
        val createdAt:String,
        val images:String?,
        @SerializedName("thumb_image")
        val thumbImage:String?,
        @SerializedName("is_hot")
        val isHot:String?,
        val category:String?,
        val tag:String?,
        val body:String,
        @SerializedName("web_name")
        val webName:String,
        @SerializedName("web_logo")
        val webLogo:String,
        @SerializedName("origin_url")
        val originUrl:String,
        val des:String
):Bean()