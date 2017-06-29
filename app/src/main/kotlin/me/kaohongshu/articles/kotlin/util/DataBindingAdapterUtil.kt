package me.kaohongshu.articles.kotlin.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Author: shichunxiang
 * Date: 2017/6/29 0029
 * Des:
 */
@BindingAdapter("load_image")
fun loadImage(imageView:ImageView,url:String?){
    Glide.with(imageView.context)
            .load(url)
            .into(imageView)
}