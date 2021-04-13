package com.novitsky.lentanewsclient.viewholders

import com.novitsky.domain.model.NewsModel

class CategoryViewHolderBinder {
    fun bind(viewHolder: CategoryViewHolder, news: NewsModel,
             listener: CategoryViewHolder.OnCategoryClickListener) {
        viewHolder.urlNews = news.guid
        viewHolder.titleView.text = news.title
        viewHolder.descriptionView.text = news.description
        viewHolder.imageView.uploadImage(news.imageURL)

        viewHolder.newsLayout.setOnClickListener {
            listener.onClick(viewHolder.urlNews)
        }
    }
}