package com.novitsky.lentanewsclient.viewholders

import com.novitsky.domain.model.News
import com.novitsky.domain.model.NewsCategory

class CatalogViewHolderBinder {
    fun bind(viewHolder: CatalogViewHolderFactory.CatalogHeaderViewHolder,
             category: NewsCategory, title: String) {
        viewHolder.category = category

        viewHolder.categoryTitle.text = title

        viewHolder.viewButton.setOnClickListener {
            viewHolder.listener.onClickCategory(viewHolder.category)
        }
    }

    fun bind(viewHolder: CatalogViewHolderFactory.CatalogNewsViewHolder, news: News) {
        viewHolder.news = news
        viewHolder.imageNews.uploadImage(news.imageURL)
        viewHolder.titleNews.text = news.title

        viewHolder.newsLayout.setOnClickListener {
            viewHolder.listener.onClickNews(viewHolder.news)
        }
    }
}
