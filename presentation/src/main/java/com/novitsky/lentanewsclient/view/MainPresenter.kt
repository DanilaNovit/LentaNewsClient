package com.novitsky.lentanewsclient.view

import android.annotation.SuppressLint
import android.widget.Toast
import com.novitsky.domain.model.NewsModel
import com.novitsky.domain.repository.LentaNetworkRepository
import com.novitsky.domain.useсases.LentaRepositoryUseCase
import com.novitsky.lentanewsclient.navigation.Router
import com.novitsky.lentanewsclient.viewholders.CatalogViewHolderFactory
import com.novitsky.lentanewsclient.viewholders.CategoryViewHolder
import java.lang.ref.WeakReference

class MainPresenter(
    private val view: WeakReference<MainContract.View>,
    private val router: Router,
    private val repositoryUseCase: LentaRepositoryUseCase
): MainContract.Presenter {
    private val catalogListener = object: CatalogViewHolderFactory.OnItemCatalogClickListener {
        override fun onClickCategory(category: LentaNetworkRepository.NewsCategory) {
            repositoryUseCase.getCategory(category, categoryCallback)
        }

        override fun onClickNews(urlNews: String) {
            router.showDetail(urlNews)
            view.get()?.setVisibleBackButton(true)
        }
    }

    private val categoryListener = object: CategoryViewHolder.OnCategoryClickListener {
        override fun onClick(urlNews: String) {
            router.showDetail(urlNews)
            view.get()?.setVisibleBackButton(true)
        }
    }

    private val catalogCallback = object: LentaRepositoryUseCase.CallbackCatalog {
        override fun onResponse(catalog: MutableMap<LentaNetworkRepository.NewsCategory, MutableList<NewsModel>>) {
            router.showCatalog(catalog, catalogListener)
        }

        @SuppressLint("ShowToast")
        override fun onFailure(errorMessage: String) {
            val toast = Toast.makeText(view.get()?.getContext(), errorMessage, Toast.LENGTH_SHORT)
            toast.show()
        }

    }

    private val categoryCallback = object: LentaRepositoryUseCase.CallbackCategory {
        override fun onResponse(news: MutableList<NewsModel>,
                                category: LentaNetworkRepository.NewsCategory) {
            router.showCategory(news, categoryListener)

            view.get()?.setVisibleBackButton(true)

            val context = view.get()?.getContext()
            if (context != null){
                view.get()?.setTitle(context.resources.getString(
                    context.resources.getIdentifier(category.value, "string", context.packageName)))
            }
        }

        override fun onFailure(errorMessage: String) {
            val toast = Toast.makeText(view.get()?.getContext(), errorMessage, Toast.LENGTH_SHORT)
            toast.show()
        }

    }

    override fun onCreateActivity() {
        repositoryUseCase.getCatalog(NUMBER_OF_NEWS_IN_CATEGORY_IN_CATALOG, catalogCallback)
    }

    override fun onClickBackButton() {
        if (view.get() != null) {
            router.back()

            if (router.isRoot()) {
                view.get()!!.setVisibleBackButton(false)

                val context = view.get()?.getContext()
                if (context != null){
                    view.get()?.setTitle(context.resources.getString(
                        context.resources.getIdentifier("app_name", "string", context.packageName)))
                }
            }
        }
    }

    companion object {
        private const val NUMBER_OF_NEWS_IN_CATEGORY_IN_CATALOG = 4
    }
}
