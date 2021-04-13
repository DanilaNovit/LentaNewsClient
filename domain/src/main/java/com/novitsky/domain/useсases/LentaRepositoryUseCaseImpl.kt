package com.novitsky.domain.useсases

import com.novitsky.domain.model.NewsModel
import com.novitsky.domain.repository.LentaNetworkRepository

class LentaRepositoryUseCaseImpl(private val repository: LentaNetworkRepository): LentaRepositoryUseCase {
    private val newsContainer = mutableListOf<NewsModel>()

    override fun getCategory(category: LentaNetworkRepository.NewsCategory,
                             callback: LentaRepositoryUseCase.CallbackCategory) {
        repository.getNews(category, object: LentaNetworkRepository.Callback {
            override fun onResponse(response: MutableList<NewsModel>, responseCode: Int) {
                when (responseCode) {
                    200  -> callback.onResponse(response, category)
                    -1   -> callback.onFailure("No network connection")
                    else -> callback.onFailure("Error $responseCode")
                }
            }
        }, newsContainer)
    }

    override fun getCatalog(numberOfNewsInCategory: Int,
                            callback: LentaRepositoryUseCase.CallbackCatalog) {
        var uploadedCategories = 0
        val resultList = mutableMapOf<LentaNetworkRepository.NewsCategory,
                MutableList<NewsModel>>()

        val categories = LentaNetworkRepository.NewsCategory.values()
        categories.forEach {
            repository.getNews(it, object: LentaNetworkRepository.Callback {
                override fun onResponse(response: MutableList<NewsModel>, responseCode: Int) {
                    if (responseCode == 200) {
                        ++uploadedCategories
                        resultList[it] = response

                        if (uploadedCategories == categories.size) {
                            callback.onResponse(resultList)
                        }
                    } else {
                        when (responseCode) {
                            -1   -> callback.onFailure("No network connection")
                            else -> callback.onFailure("Error $responseCode")
                        }
                    }
                }
            }, newsContainer, numberOfNewsInCategory)
        }
    }
}