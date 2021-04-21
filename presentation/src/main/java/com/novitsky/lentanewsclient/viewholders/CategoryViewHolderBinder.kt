package com.novitsky.lentanewsclient.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.novitsky.domain.model.News

class CategoryViewHolderBinder {
    fun bind(viewHolder: RecyclerView.ViewHolder, news: News,
             listener: CategoryViewHolder.OnCategoryClickListener) {
        if (viewHolder !is CategoryViewHolder) { return }
        viewHolder.titleView.text = news.title
        viewHolder.descriptionView.text = news.description
        viewHolder.imageView.uploadImage(news.imageURL)

        viewHolder.newsLayout.setOnClickListener {
            listener.onClick(news)
        }
    }
}