package com.novitsky.lentanewsclient.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.novitsky.domain.model.NewsModel
import com.novitsky.domain.repository.LentaNetworkRepository
import com.novitsky.lentanewsclient.R
import com.novitsky.lentanewsclient.adapters.CatalogListAdapter
import com.novitsky.lentanewsclient.adapters.CategoryListAdapter
import com.novitsky.lentanewsclient.viewholders.CatalogViewHolderFactory

class CatalogListFragment: Fragment() {
    private var recyclerView: RecyclerView? = null
    private var adapter: CatalogListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_catalog_list, container, false)

        val layoutManager =  GridLayoutManager(view.context, 2)
        layoutManager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when(position % 5) {
                    0 -> 2
                    else -> 1
                }
            }
        }

        recyclerView = view.findViewById(R.id.catalog_list)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter

        return view
    }

    fun updateAdapter(catalogMap: MutableMap<LentaNetworkRepository.NewsCategory,
            MutableList<NewsModel>>, listener: CatalogViewHolderFactory.OnItemCatalogClickListener) {
        if (adapter == null) {
            adapter = CatalogListAdapter(catalogMap, listener)
        } else {
            adapter?.updateData(catalogMap)
        }
    }
}