package com.novitsky.lentanewsclient.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.novitsky.domain.model.News
import com.novitsky.domain.model.NewsCategoryModel
import com.novitsky.lentanewsclient.viewholders.CatalogViewHolderBinder
import com.novitsky.lentanewsclient.viewholders.CatalogViewHolderFactory

class CatalogListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var listener: CatalogViewHolderFactory.OnItemCatalogClickListener
    private var items: MutableList<Any> = mutableListOf()
    private val binder = CatalogViewHolderBinder()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CatalogViewHolderFactory().create(parent, viewType, listener)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when {
            items[position] is News -> {
                binder.bind(viewHolder, items[position] as News)
            }
            items[position] is NewsCategoryModel -> {
                binder.bind(viewHolder, items[position] as NewsCategoryModel)
            }
            else -> {
                throw Exception("Unknown item type")
            }
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            items[position] is News -> {
                CatalogViewHolderFactory.NEWS_VIEW_TYPE
            }
            items[position] is NewsCategoryModel -> {
                CatalogViewHolderFactory.HEADER_VIEW_TYPE
            }
            else -> {
                throw Exception("Unknown item type")
            }
        }
    }

    fun updateData(items: MutableList<Any>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun setListener(listener: CatalogViewHolderFactory.OnItemCatalogClickListener) {
        this.listener = listener
    }
}
