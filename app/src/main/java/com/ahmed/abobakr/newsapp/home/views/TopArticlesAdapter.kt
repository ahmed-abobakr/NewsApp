package com.ahmed.abobakr.newsapp.home.views

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ahmed.abobakr.newsapp.databinding.ItemHomeNewsBinding
import com.ahmed.abobakr.newsapp.home.data.Article

class TopArticlesAdapter(private val onArticleClick: (item: Article) -> Unit): ListAdapter<Article, ViewHolder>(ItemDiffUtil) {

    private object ItemDiffUtil: DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem.url == newItem.url
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return TopArticleItem(ItemHomeNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as TopArticleItem).bind(getItem(position))
    }

    inner class TopArticleItem(private val binding: ItemHomeNewsBinding): ViewHolder(binding.root){

        fun bind(article: Article){
            with(binding){
                article.urlToImage?.let { imgNews.setImageURI(Uri.parse(it)) }
                tvNews.text = article.title
                tvSource.text = article.source?.name
            }
        }
    }
}