package com.novitsky.lentanewsclient.presenters

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.novitsky.data.repositories.LentaNetworkRepositoryImpl
import com.novitsky.domain.model.News
import com.novitsky.domain.repository.LentaNetworkRepository
import com.novitsky.domain.useсases.LentaRepositoryUseCase
import com.novitsky.domain.useсases.LentaRepositoryUseCaseImpl
import com.novitsky.lentanewsclient.activities.ActivityConfigurationModel
import com.novitsky.lentanewsclient.adapters.CatalogListAdapter
import com.novitsky.lentanewsclient.contracts.CatalogListContract
import com.novitsky.lentanewsclient.navigation.Router
import com.novitsky.lentanewsclient.viewholders.CatalogViewHolderFactory
import java.lang.ref.WeakReference

class CatalogListPresenter (
        private var router: Router
): CatalogListContract.Presenter {
    private lateinit var view: WeakReference<CatalogListContract.View>
    private val useCase: LentaRepositoryUseCase =
            LentaRepositoryUseCaseImpl(LentaNetworkRepositoryImpl())

    fun setView(view: CatalogListContract.View) {
        this.view = WeakReference(view)
    }


    override fun onViewCreated() {
        setConfig()

        useCase.getCatalog(NUMBER_OF_NEWS_IN_CATEGORY_IN_CATALOG, catalogCallback)

        val layoutManager =  GridLayoutManager(view.get()?.getContext(), 2)
        layoutManager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when(position % 5) {
                    0 -> 2
                    else -> 1
                }
            }
        }

        view.get()?.setLayoutManager(layoutManager)
    }

    private val catalogCallback = object: LentaRepositoryUseCase.CallbackCatalog {
        override fun onResponse(catalog: MutableMap<LentaNetworkRepository.NewsCategory, MutableList<News>>) {
            view.get()?.setAdapter(CatalogListAdapter(catalog, catalogListener))
        }

        @SuppressLint("ShowToast")
        override fun onFailure(errorMessage: String) {
            val toast = Toast.makeText(view.get()?.getContext(), errorMessage, Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    private val catalogListener = object: CatalogViewHolderFactory.OnItemCatalogClickListener {
        override fun onClickCategory(category: LentaNetworkRepository.NewsCategory) {
            router.showCategory(category)
        }

        override fun onClickNews(urlNews: String) {
            router.showDetail(urlNews)
        }
    }

    private fun setConfig() {
        val context = view.get()?.getContext()
        val res = context?.resources
        val title = res?.getString(res.getIdentifier("app_name", "string", context.packageName))

        router.configure(ActivityConfigurationModel(
                title = title!!,
                backButtonEnabled = false,
                backButtonClosesActivity = true
        ))
    }

    companion object {
        private const val NUMBER_OF_NEWS_IN_CATEGORY_IN_CATALOG = 4
    }
}