package com.novitsky.domain.useсases

import com.novitsky.domain.model.NewsModel
import com.novitsky.domain.repository.LentaNetworkRepository

class LentaRepositoryUseCaseImpl(private val repository: LentaNetworkRepository): LentaRepositoryUseCase {

    override fun getCategory(category: String, callback: LentaRepositoryUseCase.CallbackCategory) {
        repository.getNews(category, object: LentaNetworkRepository.Callback {
            override fun onResponse(response: MutableList<NewsModel>, responseCode: Int) {
                when (responseCode) {
                    200  -> callback.onResponse(response)
                    -1   -> callback.onFailure("No network connection")
                    else -> callback.onFailure("Error $responseCode")
                }
            }
        })
    }

    override fun getCatalog(numberOfNewsInCategory: Int,
                            callback: LentaRepositoryUseCase.CallbackCatalog) {
        var uploadedCategories = 0
        val resultList = mutableMapOf<String, MutableList<NewsModel>>()

        val categories = LentaNetworkRepository.NewsCategory.values()
        categories.forEach {
            repository.getNews(it.value, object: LentaNetworkRepository.Callback {
                override fun onResponse(response: MutableList<NewsModel>, responseCode: Int) {
                    if (responseCode == 200) {
                        ++uploadedCategories
                        resultList[it.value] = response

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
            }, numberOfNewsInCategory)
        }
    }
}