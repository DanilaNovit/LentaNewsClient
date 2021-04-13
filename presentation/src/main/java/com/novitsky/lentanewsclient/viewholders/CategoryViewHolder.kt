package com.novitsky.lentanewsclient.viewholders

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.novitsky.lentanewsclient.R
import com.novitsky.lentanewsclient.customview.UrlImageView

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    interface OnCategoryClickListener {
        fun onClick(urlNews: String)
    }

    lateinit var urlNews: String
    val newsLayout: LinearLayout = itemView.findViewById(R.id.category_list_item)
    val titleView: TextView = itemView.findViewById(R.id.title_news_category_list)
    val imageView: UrlImageView = itemView.findViewById(R.id.image_news_category_list)
    val descriptionView: TextView = itemView
        .findViewById(R.id.description_news_category_list)
}