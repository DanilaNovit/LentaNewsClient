package com.novitsky.lentanewsclient.adapters

import androidx.recyclerview.widget.RecyclerView
import com.novitsky.domain.model.News
import com.novitsky.lentanewsclient.viewholders.CategoryViewHolder

interface CategoryListAdapter {
    fun updateData(newsList: MutableList<News>?)
    fun setListener(listener: CategoryViewHolder.OnCategoryClickListener)
    fun self(): RecyclerView.Adapter<RecyclerView.ViewHolder>
}
