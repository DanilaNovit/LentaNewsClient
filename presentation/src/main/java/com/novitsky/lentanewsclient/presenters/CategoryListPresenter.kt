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
    private var title: String? = null

    fun setView(view: CategoryListContract.View) {
        this.view = WeakReference(view)
    }

    override fun onViewCreated() {
        when(category) {
            NewsCategory.RUSSIA -> useCase.getRussianCategory(categoryCallback)
            NewsCategory.WORLD -> useCase.getWorldCategory(categoryCallback)
            NewsCategory.USSR -> useCase.getUssrCategory(categoryCallback)
            NewsCategory.ECONOMICS -> useCase.getEconomicsCategory(categoryCallback)
            NewsCategory.SCIENCE -> useCase.getScienceCategory(categoryCallback)
            NewsCategory.CULTURE -> useCase.getCultureCategory(categoryCallback)
            NewsCategory.SPORT -> useCase.getSportCategory(categoryCallback)
            NewsCategory.MEDIA -> useCase.getMediaCategory(categoryCallback)
            NewsCategory.STYLE -> useCase.getStyleCategory(categoryCallback)
            NewsCategory.TRAVEL -> useCase.getTravelCategory(categoryCallback)
            NewsCategory.LIFE -> useCase.getLifeCategory(categoryCallback)
            NewsCategory.REALTY -> useCase.getRealtyCategory(categoryCallback)
        }
    }

    override fun onClickItemNews(item: News) {
        router.showDetail(item.guid)
    }

    override fun onFragmentLastInBackStack() {
        if (title != null) {
            view.get()?.updateTitle(title.toString())
        }
    }

    private val categoryCallback = object: LentaRepositoryUseCase.CallbackCategory {
        override fun onResponse(news: MutableList<News>, category: NewsCategory) {
                view.get()?.updateData(news)

                title = news[0].category
                view.get()?.updateTitle(title.toString())
            }

        override fun onFailure(errorMessage: String) {
            view.get()?.showError(errorMessage)
        }
    }
}
