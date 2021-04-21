package com.novitsky.lentanewsclient.fragments

import androidx.fragment.app.Fragment
import com.novitsky.data.repositories.LentaNetworkRepositoryImpl
import com.novitsky.domain.useсases.GetCatalogUseCaseImpl
import com.novitsky.domain.useсases.GetCategoryUseCaseImpl
import com.novitsky.lentanewsclient.adapters.CatalogListAdapter
import com.novitsky.lentanewsclient.adapters.CategoryListAdapter
import com.novitsky.lentanewsclient.navigation.Router
import com.novitsky.lentanewsclient.presenters.CatalogListPresenter
import com.novitsky.lentanewsclient.presenters.CategoryListPresenter
import com.novitsky.lentanewsclient.presenters.NewsDetailPresenter

class FragmentFactoryImpl: FragmentFactory {
    private val repository = LentaNetworkRepositoryImpl()

    override fun createCatalog(router: Router): Fragment {
        val presenter = CatalogListPresenter(router, GetCatalogUseCaseImpl(repository))
        val view = CatalogListFragment()

        view.setArguments(presenter, CatalogListAdapter())
        presenter.setView(view)

        return view
    }

    override fun createCategory(router: Router, categoryID: Int): Fragment {
        val presenter = CategoryListPresenter(categoryID, router,
                                              GetCategoryUseCaseImpl(repository))
        val view = CategoryListFragment()

        view.setArguments(presenter, CategoryListAdapter())
        presenter.setView(view)

        return view
    }

    override fun createDetailNews(id: String): Fragment {
        val presenter = NewsDetailPresenter(id)
        val view = NewsDetailFragment()

        view.setArguments(presenter)
        presenter.setView(view)

        return view
    }
}
