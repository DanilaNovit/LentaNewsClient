package com.novitsky.lentanewsclient.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.novitsky.domain.model.News
import com.novitsky.domain.model.NewsCategoryModel

class CatalogViewHolderBinder {
    fun bind(viewHolder: RecyclerView.ViewHolder, category: NewsCategoryModel) {
        if (viewHolder !is CatalogViewHolderFactory.CatalogHeaderViewHolder) { return }
        viewHolder.categoryTitle.text = category.name

        viewHolder.viewButton.setOnClickListener {
            viewHolder.listener.onClickCategory(category.id)
        }
    }

    fun bind(viewHolder: RecyclerView.ViewHolder, news: News) {
        if (viewHolder !is CatalogViewHolderFactory.CatalogNewsViewHolder) { return }
        viewHolder.imageNews.uploadImage(news.imageURL)
        viewHolder.titleNews.text = news.title

        viewHolder.newsLayout.setOnClickListener {
            viewHolder.listener.onClickNews(news)
        }
    }
}
