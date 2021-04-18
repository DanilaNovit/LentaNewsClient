package com.novitsky.lentanewsclient.presenters

import android.annotation.SuppressLint
import com.novitsky.data.repositories.LentaNetworkRepositoryImpl
import com.novitsky.domain.model.News
import com.novitsky.domain.model.NewsCategory
import com.novitsky.domain.useсases.LentaRepositoryUseCase
import com.novitsky.domain.useсases.LentaRepositoryUseCaseImpl
import com.novitsky.lentanewsclient.contracts.CatalogListContract
import com.novitsky.lentanewsclient.navigation.Router
import java.lang.ref.WeakReference

class CatalogListPresenter (private var router: Router): CatalogListContract.Presenter {
    private lateinit var view: WeakReference<CatalogListContract.View>
    private val useCase: LentaRepositoryUseCase =
            LentaRepositoryUseCaseImpl(LentaNetworkRepositoryImpl())

    fun setView(view: CatalogListContract.View) {
        this.view = WeakReference(view)
    }


    override fun onViewCreated() {
        useCase.getCatalog(catalogCallback)
    }

    override fun onCategoryItemClicked(category: NewsCategory) {
        router.showCategory(category)
    }

    override fun onNewsItemClicked(item: News) {
        router.showDetail(item.guid)
    }

    private val catalogCallback = object: LentaRepositoryUseCase.CallbackCatalog {
        override fun onResponse(catalog: MutableMap<NewsCategory, MutableList<News>>) {
            view.get()?.updateData(catalog)
        }

        @SuppressLint("ShowToast")
        override fun onFailure(errorMessage: String) {
            view.get()?.showError(errorMessage)
        }
    }
}