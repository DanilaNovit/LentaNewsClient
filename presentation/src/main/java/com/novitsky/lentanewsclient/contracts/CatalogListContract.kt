package com.novitsky.lentanewsclient.contracts

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.novitsky.lentanewsclient.adapters.CatalogListAdapter

interface CatalogListContract {
    interface View {
        fun setAdapter(adapter: CatalogListAdapter)
        fun setLayoutManager(layoutManager: RecyclerView.LayoutManager)
        fun visibilityProgressBar(visibility: Boolean)
        fun getContext(): Context?
    }

    interface Presenter {
        fun onViewCreated()
    }
}