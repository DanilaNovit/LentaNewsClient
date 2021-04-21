package com.novitsky.lentanewsclient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.novitsky.domain.model.News
import com.novitsky.lentanewsclient.R
import com.novitsky.lentanewsclient.viewholders.CategoryViewHolder
import com.novitsky.lentanewsclient.viewholders.CategoryViewHolderBinder
import java.lang.ref.WeakReference

class CategoryListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var listener: WeakReference<CategoryViewHolder.OnCategoryClickListener>
    private var newsList: MutableList<News>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_category_list, viewGroup, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (newsList == null || listener.get() == null) { return }
        CategoryViewHolderBinder().bind(viewHolder, newsList!![position], listener.get()!!)
    }

    override fun getItemCount(): Int {
        return newsList?.size ?: 0
    }

    fun updateData(newsList: MutableList<News>?) {
        this.newsList = newsList
        notifyDataSetChanged()
    }

    fun setListener(listener: CategoryViewHolder.OnCategoryClickListener) {
        this.listener = WeakReference(listener)
    }
}
