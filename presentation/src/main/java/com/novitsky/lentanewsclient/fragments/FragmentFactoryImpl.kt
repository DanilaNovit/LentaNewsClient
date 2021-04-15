package com.novitsky.lentanewsclient.fragments

import androidx.fragment.app.Fragment
import com.novitsky.domain.repository.LentaNetworkRepository
import com.novitsky.lentanewsclient.navigation.Router
import com.novitsky.lentanewsclient.presenters.CatalogListPresenter
import com.novitsky.lentanewsclient.presenters.CategoryListPresenter
import com.novitsky.lentanewsclient.presenters.NewsDetailPresenter

class FragmentFactoryImpl: FragmentFactory {
    override fun createCatalog(router: Router): Fragment {
        val presenter = CatalogListPresenter(router)
        val view = CatalogListFragment(presenter)
        presenter.setView(view)
        return view
    }

    override fun createCategory(router: Router,
                                category: LentaNetworkRepository.NewsCategory): Fragment {
        val presenter = CategoryListPresenter(category, router)
        val view = CategoryListFragment(presenter)
        presenter.setView(view)
        return view
    }

    override fun createDetailNews(router: Router, url: String): Fragment {
        val presenter = NewsDetailPresenter(router, url)
        val view = NewsDetailFragment(presenter)
        presenter.setView(view)
        return view
    }
}