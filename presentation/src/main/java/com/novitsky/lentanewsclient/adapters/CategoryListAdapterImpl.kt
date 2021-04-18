package com.novitsky.lentanewsclient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.novitsky.domain.model.News
import com.novitsky.lentanewsclient.R
import com.novitsky.lentanewsclient.viewholders.CategoryViewHolder
import com.novitsky.lentanewsclient.viewholders.CategoryViewHolderBinder

class CategoryListAdapterImpl: RecyclerView.Adapter<RecyclerView.ViewHolder>(), CategoryListAdapter {
    private lateinit var listener: CategoryViewHolder.OnCategoryClickListener
    private var newsList: MutableList<News>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_category_list, viewGroup, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (newsList == null) { return }

        if (viewHolder is CategoryViewHolder){
            CategoryViewHolderBinder().bind(viewHolder, newsList!![position], listener)
        }
    }

    override fun getItemCount(): Int {
        return newsList?.size ?: 0
    }

    override fun updateData(newsList: MutableList<News>?) {
        this.newsList = newsList
        notifyDataSetChanged()
    }

    override fun setListener(listener: CategoryViewHolder.OnCategoryClickListener) {
        this.listener = listener
    }

    override fun self(): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        return this
    }
}
