package me.kaohongshu.articles.kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import me.kaohongshu.articles.kotlin.data.bean.Article
import me.kaohongshu.articles.kotlin.databinding.ItemArticleType1Binding
import me.kaohongshu.articles.kotlin.databinding.ItemFooterMoreBinding

/**
 * Author: shichunxiang
 * Date: 2017/6/29 0029
 * Des:
 */
class ArticlesAdapter(val mList: List<Article>) : BaseViewAdapter() {
    override fun onBindViewHolder(holder: RecyclerViewHolder?, position: Int) {
        val viewBinding=holder?.viewBinding
        if(viewBinding is ItemArticleType1Binding){
            viewBinding.data=mList[position]
            viewBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerViewHolder = when (viewType) {
        1 -> RecyclerViewHolder(ItemArticleType1Binding.inflate(LayoutInflater.from(parent?.context), parent, false))
        2 -> RecyclerViewHolder(ItemFooterMoreBinding.inflate(LayoutInflater.from(parent?.context), parent, false))
        else -> RecyclerViewHolder(ItemFooterMoreBinding.inflate(LayoutInflater.from(parent?.context), parent, false))
    }

    override fun getItemCount(): Int = if (mList.size == 0) 0 else mList.size + 1

    override fun getItemViewType(position: Int): Int = if (position + 1 ==itemCount) 2 else 1
}