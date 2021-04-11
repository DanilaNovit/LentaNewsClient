package com.novitsky.lentanewsclient.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.novitsky.domain.model.NewsModel
import com.novitsky.lentanewsclient.R
import com.novitsky.lentanewsclient.customview.UrlImageView

class CategoryListAdapter(private val newsList: MutableList<NewsModel>):
    RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_category_list, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(newsList[position])
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        private val titleView: TextView? = itemView?.findViewById(R.id.title_news_category_list)
        private val imageView: UrlImageView? = itemView?.findViewById(R.id.image_news_category_list)
        private val descriptionView: TextView? = itemView
                                ?.findViewById(R.id.description_news_category_list)

        fun bind(news: NewsModel) {
            titleView?.text = news.title
            descriptionView?.text = news.description
            imageView?.uploadImage(news.imageURL.toString())
        }
    }
}