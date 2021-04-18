package com.novitsky.lentanewsclient.adapters

import androidx.recyclerview.widget.RecyclerView
import com.novitsky.domain.model.News
import com.novitsky.domain.model.NewsCategory
import com.novitsky.lentanewsclient.viewholders.CatalogViewHolderFactory

interface CatalogListAdapter {
    fun updateData(catalogMap: MutableMap<NewsCategory, MutableList<News>>?)
    fun setListener(listener: CatalogViewHolderFactory.OnItemCatalogClickListener)
    fun self(): RecyclerView.Adapter<RecyclerView.ViewHolder>
}