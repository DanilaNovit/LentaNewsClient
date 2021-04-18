package com.novitsky.lentanewsclient.fragments

import androidx.fragment.app.Fragment
import com.novitsky.domain.model.NewsCategory
import com.novitsky.lentanewsclient.adapters.CatalogListAdapterImpl
import com.novitsky.lentanewsclient.adapters.CategoryListAdapterImpl
import com.novitsky.lentanewsclient.navigation.Router
import com.novitsky.lentanewsclient.presenters.CatalogListPresenter
import com.novitsky.lentanewsclient.presenters.CategoryListPresenter

class FragmentFactoryImpl: FragmentFactory {
    override fun createCatalog(router: Router): Fragment {
        val presenter = CatalogListPresenter(router)
        val view = CatalogListFragment(presenter, CatalogListAdapterImpl())
        presenter.setView(view)
        return view
    }

    override fun createCategory(router: Router, category: NewsCategory): Fragment {
        val presenter = CategoryListPresenter(category, router)
        val view = CategoryListFragment(presenter, CategoryListAdapterImpl())
        presenter.setView(view)
        return view
    }

    override fun createDetailNews(url: String): Fragment {
        return NewsDetailFragment(url)
    }
}