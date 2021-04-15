package com.novitsky.lentanewsclient.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.novitsky.domain.useсases.LentaRepositoryUseCase
import com.novitsky.lentanewsclient.R
import com.novitsky.lentanewsclient.adapters.CatalogListAdapter
import com.novitsky.lentanewsclient.contracts.CatalogListContract
import com.novitsky.lentanewsclient.navigation.Router
import com.novitsky.lentanewsclient.presenters.CatalogListPresenter

class CatalogListFragment: Fragment, CatalogListContract.View {
    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: CatalogListContract.Presenter

    constructor(): super()

    constructor(presenter: CatalogListContract.Presenter) {
        this.presenter = presenter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_catalog_list, container, false)
        recyclerView = view.findViewById(R.id.catalog_list)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun setAdapter(adapter: CatalogListAdapter) {
        recyclerView.adapter = adapter
    }

    override fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        recyclerView.layoutManager = layoutManager
    }
}