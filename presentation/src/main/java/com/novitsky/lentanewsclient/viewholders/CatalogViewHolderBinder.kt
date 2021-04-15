package com.novitsky.lentanewsclient.viewholders

import com.novitsky.domain.model.News
import com.novitsky.domain.repository.LentaNetworkRepository

class CatalogViewHolderBinder {
    fun bind(viewHolder: CatalogViewHolderFactory.CatalogHeaderViewHolder,
             category: LentaNetworkRepository.NewsCategory) {
        viewHolder.category = category

        val context = viewHolder.itemView.context
        viewHolder.categoryTitle.text = context.resources.getString(
            context.resources.getIdentifier(category.value, "string", context.packageName))

        viewHolder.viewButton.setOnClickListener {
            viewHolder.listener.onClickCategory(viewHolder.category)
        }
    }

    fun bind(viewHolder: CatalogViewHolderFactory.CatalogNewsViewHolder, news: News) {
        viewHolder.url = news.guid
        viewHolder.imageNews.uploadImage(news.imageURL)
        viewHolder.titleNews.text = news.title

        viewHolder.newsLayout.setOnClickListener {
            viewHolder.listener.onClickNews(viewHolder.url)
        }
    }
}
