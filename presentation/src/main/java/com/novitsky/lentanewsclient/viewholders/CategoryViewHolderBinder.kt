package com.novitsky.lentanewsclient.viewholders

import com.novitsky.domain.model.News

class CategoryViewHolderBinder {
    fun bind(viewHolder: CategoryViewHolder, news: News,
             listener: CategoryViewHolder.OnCategoryClickListener) {
        viewHolder.news = news
        viewHolder.titleView.text = news.title
        viewHolder.descriptionView.text = news.description
        viewHolder.imageView.uploadImage(news.imageURL)

        viewHolder.newsLayout.setOnClickListener {
            listener.onClick(viewHolder.news)
        }
    }
}