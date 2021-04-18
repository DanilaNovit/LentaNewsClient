package com.novitsky.lentanewsclient.presenters

import com.novitsky.data.repositories.LentaNetworkRepositoryImpl
import com.novitsky.domain.model.News
import com.novitsky.domain.model.NewsCategory
import com.novitsky.domain.useсases.LentaRepositoryUseCase
import com.novitsky.domain.useсases.LentaRepositoryUseCaseImpl
import com.novitsky.lentanewsclient.contracts.CategoryListContract
import com.novitsky.lentanewsclient.navigation.Router
import java.lang.ref.WeakReference

class CategoryListPresenter(
        private val category: NewsCategory,
        private val router: Router
): CategoryListContract.Presenter {
    private lateinit var view: WeakReference<CategoryListContract.View>
    private val useCase: LentaRepositoryUseCase =
            LentaRepositoryUseCaseImpl(LentaNetworkRepositoryImpl())

    fun setView(view: CategoryListContract.View) {
        this.view = WeakReference(view)
    }

    override fun onViewCreated() {
        useCase.getCategory(category, categoryCallback)
    }

    override fun onClickItemNews(item: News) {
        router.showDetail(item.guid)
    }

    private val categoryCallback = object: LentaRepositoryUseCase.CallbackCategory {
        override fun onResponse(news: MutableList<News>, category: NewsCategory) {
                view.get()?.updateData(news)
            }

        override fun onFailure(errorMessage: String) {
            view.get()?.showError(errorMessage)
        }
    }
}
