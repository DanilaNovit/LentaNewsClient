package com.novitsky.lentanewsclient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.novitsky.domain.model.NewsModel
import com.novitsky.lentanewsclient.R
import com.novitsky.lentanewsclient.viewholders.CategoryViewHolder
import com.novitsky.lentanewsclient.viewholders.CategoryViewHolderBinder

class CategoryListAdapter(
    private var newsList: MutableList<NewsModel>,
    private val listener: CategoryViewHolder.OnCategoryClickListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_category_list, viewGroup, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is CategoryViewHolder){
            CategoryViewHolderBinder().bind(viewHolder, newsList[position], listener)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun updateData(newsList: MutableList<NewsModel>) {
        this.newsList = newsList
        notifyDataSetChanged()
    }
}
