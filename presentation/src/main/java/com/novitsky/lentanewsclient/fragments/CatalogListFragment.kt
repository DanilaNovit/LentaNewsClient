package com.novitsky.lentanewsclient.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.novitsky.domain.model.News
import com.novitsky.domain.model.NewsCategory
import com.novitsky.lentanewsclient.R
import com.novitsky.lentanewsclient.adapters.CatalogListAdapter
import com.novitsky.lentanewsclient.contracts.CatalogListContract
import com.novitsky.lentanewsclient.viewholders.CatalogViewHolderFactory

class CatalogListFragment: ActionBarFragment,
        CatalogListContract.View, FragmentManager.OnBackStackChangedListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: CatalogListContract.Presenter
    private lateinit var adapter: CatalogListAdapter

    constructor(): super()

    constructor(presenter: CatalogListContract.Presenter, adapter: CatalogListAdapter) {
        this.presenter = presenter
        this.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.supportFragmentManager?.addOnBackStackChangedListener(this)

        val view = inflater.inflate(R.layout.fragment_catalog_list, container, false)

        recyclerView = view.findViewById(R.id.catalog_list)
        progressBar  = view.findViewById(R.id.catalog_progress_bar)

        val layoutManager =  GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when(position % 5) {
                    0 -> 2
                    else -> 1
                }
            }
        }

        adapter.setListener(catalogListener)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter.self()
        progressBar.visibility = ProgressBar.VISIBLE

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    private val catalogListener = object: CatalogViewHolderFactory.OnItemCatalogClickListener {
        override fun onClickCategory(category: NewsCategory) {
            presenter.onCategoryItemClicked(category)
        }

        override fun onClickNews(item: News) {
            presenter.onNewsItemClicked(item)
        }
    }

    override fun updateData(catalogMap: MutableMap<NewsCategory, MutableList<News>>) {
        adapter.updateData(catalogMap)
        progressBar.visibility = ProgressBar.INVISIBLE
    }

    override fun showError(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onBackStackChanged() {
        updateTitle(getString(R.string.app_name))
    }
}