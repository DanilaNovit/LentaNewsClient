package com.novitsky.lentanewsclient.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.novitsky.domain.model.News
import com.novitsky.lentanewsclient.R
import com.novitsky.lentanewsclient.customview.UrlImageView
import java.lang.IllegalArgumentException

class CatalogViewHolderFactory {
    interface OnItemCatalogClickListener {
        fun onClickCategory(categoryID: Int)
        fun onClickNews(item: News)
    }

    class CatalogHeaderViewHolder(
        itemView: View,
        val listener: OnItemCatalogClickListener
    ): RecyclerView.ViewHolder(itemView) {
        val categoryTitle: TextView = itemView.findViewById(R.id.category_title)
        val viewButton: TextView = itemView.findViewById(R.id.view_button)
    }

    class CatalogNewsViewHolder(
        itemView: View,
        val listener: OnItemCatalogClickListener
    ): RecyclerView.ViewHolder(itemView) {
        val newsLayout: LinearLayout = itemView.findViewById(R.id.layout_news)
        val imageNews: UrlImageView = itemView.findViewById(R.id.image_news)
        val titleNews: TextView = itemView.findViewById(R.id.title_news)
    }

    fun create(parent: ViewGroup, viewType: Int,
               listener: OnItemCatalogClickListener): RecyclerView.ViewHolder {
        when(viewType) {
            HEADER_VIEW_TYPE -> {
                val headerViewType = LayoutInflater.from(parent.context)
                    .inflate(R.layout.header_item_catalog_list, parent, false)
                return CatalogHeaderViewHolder(headerViewType, listener)
            }

            NEWS_VIEW_TYPE -> {
                val newsViewType = LayoutInflater.from(parent.context)
                    .inflate(R.layout.news_item_catalog_list, parent, false)
                return CatalogNewsViewHolder(newsViewType, listener)
            }
        }

        throw IllegalArgumentException()
    }

    companion object {
        const val HEADER_VIEW_TYPE = 0
        const val NEWS_VIEW_TYPE = 1
    }
}