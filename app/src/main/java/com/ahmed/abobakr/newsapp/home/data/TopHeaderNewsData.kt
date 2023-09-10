package com.ahmed.abobakr.newsapp.home.data

import android.os.Parcelable
import com.ahmed.abobakr.newsapp.base.BaseResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(val id: String?, val name: String?): Parcelable

@Parcelize
data class Article(val source: Source?, val author: String?, val title: String?, val description: String?, val url: String?,
                   val urlToImage: String?, val publishedAt: String?, val content: String?): Parcelable

class  TopHeaderNewsResponse(status: String, val totalResults: Int?, val articles: List<Article>): BaseResponse(status)