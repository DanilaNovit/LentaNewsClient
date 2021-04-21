package com.novitsky.lentanewsclient.presenters

import com.novitsky.domain.model.News
import com.novitsky.domain.useсases.GetCategoryUseCase
import com.novitsky.lentanewsclient.contracts.CategoryListContract
import com.novitsky.lentanewsclient.navigation.Router
import java.lang.ref.WeakReference

class CategoryListPresenter(
        private val categoryID: Int,
        private val router: Router,
        private val useCase: GetCategoryUseCase
): CategoryListContract.Presenter {
    private lateinit var view: WeakReference<CategoryListContract.View>
    private var title: String? = null

    fun setView(view: CategoryListContract.View) {
        this.view = WeakReference(view)
    }

    override fun onViewCreated() {
        useCase.get(categoryID, callback)
    }

    override fun onClickItemNews(item: News) {
        router.showNewsDetail(item.ID)
    }

    override fun onFragmentLastInBackStack() {
        if (title != null) {
            view.get()?.updateTitle(title.toString())
        }
    }

    private val callback = object: GetCategoryUseCase.Callback {
        override fun onResponse(news: MutableList<News>) {
                view.get()?.updateData(news)

                title = news[0].category.name
                view.get()?.updateTitle(title.toString())
            }

        override fun onFailure(errorMessage: String) {
            view.get()?.showError(errorMessage)
        }
    }
}
