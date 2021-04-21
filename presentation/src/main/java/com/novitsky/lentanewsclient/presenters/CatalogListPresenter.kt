package com.novitsky.lentanewsclient.presenters

import android.annotation.SuppressLint
import com.novitsky.data.mappers.NewsMapper
import com.novitsky.domain.model.News
import com.novitsky.domain.model.NewsCategory
import com.novitsky.domain.useсases.GetCatalogUseCase
import com.novitsky.lentanewsclient.contracts.CatalogListContract
import com.novitsky.lentanewsclient.navigation.Router
import java.lang.ref.WeakReference

class CatalogListPresenter (
        private var router: Router,
        private val useCase: GetCatalogUseCase
): CatalogListContract.Presenter {
    private lateinit var view: WeakReference<CatalogListContract.View>

    fun setView(view: CatalogListContract.View) {
        this.view = WeakReference(view)
    }


    override fun onViewCreated() {
        useCase.get(callback)
    }

    override fun onCategoryItemClicked(categoryID: Int) {
        router.showCategory(categoryID)
    }

    override fun onNewsItemClicked(item: News) {
        router.showNewsDetail(item.ID)
    }

    private val callback = object: GetCatalogUseCase.Callback {
        override fun onResponse(catalog: MutableMap<Int, MutableList<News>>) {
            val items = mutableListOf<Any>()
            val mapper = NewsMapper()

            loop@ for (category in NewsCategory.values()) {
                val categoryList = catalog[mapper.getIdByCategoryNews(category)] ?: continue

                items.add(categoryList[0].category)

                for (i in 0..3) {
                    items.add(categoryList[i])
                }
            }

            view.get()?.updateData(items)
        }

        @SuppressLint("ShowToast")
        override fun onFailure(errorMessage: String) {
            view.get()?.showError(errorMessage)
        }
    }
}
