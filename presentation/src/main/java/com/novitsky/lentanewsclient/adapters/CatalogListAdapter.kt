package com.novitsky.lentanewsclient.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.novitsky.domain.model.NewsModel
import com.novitsky.domain.repository.LentaNetworkRepository
import com.novitsky.lentanewsclient.viewholders.CatalogViewHolderBinder
import com.novitsky.lentanewsclient.viewholders.CatalogViewHolderFactory

class CatalogListAdapter(
    private var catalogMap: MutableMap<LentaNetworkRepository.NewsCategory, MutableList<NewsModel>>,
    private val listener: CatalogViewHolderFactory.OnItemCatalogClickListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var catalogMapKeys = LentaNetworkRepository.NewsCategory.values()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CatalogViewHolderFactory().create(parent, viewType, listener)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val mapKey = catalogMapKeys[position / 5]

        when (viewHolder) {
            is CatalogViewHolderFactory.CatalogHeaderViewHolder -> {
                CatalogViewHolderBinder().bind(viewHolder, mapKey)
            }

            is CatalogViewHolderFactory.CatalogNewsViewHolder -> {
                val positionInList = (position % 5) - 1
                CatalogViewHolderBinder().bind(viewHolder, catalogMap[mapKey]!![positionInList])
            }
        }
    }

    override fun getItemCount(): Int {
        return catalogMap.size + (catalogMapKeys.size * 4)
    }

    override fun getItemViewType(position: Int): Int {
        return if ((position % 5) == 0) {
            CatalogViewHolderFactory.HEADER_VIEW_TYPE
        } else {
            CatalogViewHolderFactory.NEWS_VIEW_TYPE
        }
    }

    fun updateData(catalogMap: MutableMap<LentaNetworkRepository.NewsCategory,
                MutableList<NewsModel>>) {
        this.catalogMap = catalogMap
        notifyDataSetChanged()
    }
}