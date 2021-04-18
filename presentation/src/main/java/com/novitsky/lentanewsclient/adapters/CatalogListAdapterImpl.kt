package com.novitsky.lentanewsclient.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.novitsky.domain.model.News
import com.novitsky.domain.model.NewsCategory
import com.novitsky.lentanewsclient.viewholders.CatalogViewHolderBinder
import com.novitsky.lentanewsclient.viewholders.CatalogViewHolderFactory

class CatalogListAdapterImpl: RecyclerView.Adapter<RecyclerView.ViewHolder>(), CatalogListAdapter {
    private var catalogMapKeys = NewsCategory.values()
    private lateinit var listener: CatalogViewHolderFactory.OnItemCatalogClickListener
    private var catalogMap: MutableMap<NewsCategory, MutableList<News>>?
                    = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CatalogViewHolderFactory().create(parent, viewType, listener)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (catalogMap == null) { return }

        val mapKey = catalogMapKeys[position / 5]

        when (viewHolder) {
            is CatalogViewHolderFactory.CatalogHeaderViewHolder -> {
                val title = catalogMap?.get(mapKey)?.get(0)?.category

                if (title != null) {
                    CatalogViewHolderBinder().bind(viewHolder, mapKey, title)
                }
            }

            is CatalogViewHolderFactory.CatalogNewsViewHolder -> {
                val positionInList = (position % 5) - 1
                val news = catalogMap?.get(mapKey)?.get(positionInList)

                if (news != null) {
                    CatalogViewHolderBinder().bind(viewHolder, news)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (catalogMap != null) {
            catalogMap!!.size + (catalogMapKeys.size * 4)
        } else {
            0
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if ((position % 5) == 0) {
            CatalogViewHolderFactory.HEADER_VIEW_TYPE
        } else {
            CatalogViewHolderFactory.NEWS_VIEW_TYPE
        }
    }

    override fun updateData(catalogMap: MutableMap<NewsCategory, MutableList<News>>?) {
        this.catalogMap = catalogMap
        notifyDataSetChanged()
    }

    override fun setListener(listener: CatalogViewHolderFactory.OnItemCatalogClickListener) {
        this.listener = listener
    }

    override fun self(): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        return this
    }
}
