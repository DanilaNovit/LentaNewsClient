package com.novitsky.lentanewsclient.presenters

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.novitsky.data.repositories.LentaNetworkRepositoryImpl
import com.novitsky.domain.model.News
import com.novitsky.domain.repository.LentaNetworkRepository
import com.novitsky.domain.useсases.LentaRepositoryUseCase
import com.novitsky.domain.useсases.LentaRepositoryUseCaseImpl
import com.novitsky.lentanewsclient.activities.ActivityConfigurationModel
import com.novitsky.lentanewsclient.adapters.CategoryListAdapter
import com.novitsky.lentanewsclient.contracts.CategoryListContract
import com.novitsky.lentanewsclient.navigation.Router
import com.novitsky.lentanewsclient.viewholders.CategoryViewHolder
import java.lang.ref.WeakReference

class CategoryListPresenter(
        private val category: LentaNetworkRepository.NewsCategory,
        private val router: Router
): CategoryListContract.Presenter {
    private lateinit var view: WeakReference<CategoryListContract.View>
    private val useCase: LentaRepositoryUseCase =
            LentaRepositoryUseCaseImpl(LentaNetworkRepositoryImpl())

    fun setView(view: CategoryListContract.View) {
        this.view = WeakReference(view)
    }

    override fun onViewCreated() {
        setConfig()
        view.get()?.visibilityProgressBar(true)

        useCase.getCategory(category, categoryCallback)
        view.get()?.setLayoutManager(LinearLayoutManager(view.get()?.getContext()))
    }

    private val categoryListener = object: CategoryViewHolder.OnCategoryClickListener {
        override fun onClick(urlNews: String) {
            router.showDetail(urlNews)
        }
    }

    private val categoryCallback = object: LentaRepositoryUseCase.CallbackCategory {
        override fun onResponse(news: MutableList<News>,
                                category: LentaNetworkRepository.NewsCategory) {
                view.get()?.visibilityProgressBar(false)
                view.get()?.setAdapter(CategoryListAdapter(news, categoryListener))
            }

        override fun onFailure(errorMessage: String) {
            val toast = Toast.makeText(view.get()?.getContext(), errorMessage, Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    private fun setConfig() {
        val context = view.get()?.getContext()
        val res = context?.resources
        val title = res?.getString(res.getIdentifier(category.value, "string", context.packageName))

        router.configure(ActivityConfigurationModel(
                title = title!!,
                backButtonEnabled = true,
                backButtonClosesActivity = false
        ))
    }
}