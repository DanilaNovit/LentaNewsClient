package com.novitsky.domain.useсases

import com.novitsky.domain.model.News
import com.novitsky.domain.model.NewsCategory
import com.novitsky.domain.repository.LentaNetworkRepository

class LentaRepositoryUseCaseImpl(
        private val repository: LentaNetworkRepository
): LentaRepositoryUseCase {
    override fun getCategory(category: NewsCategory,
                             callback: LentaRepositoryUseCase.CallbackCategory) {
        repository.getNews(object: LentaNetworkRepository.Callback {
            override fun onResponse(response: MutableMap<NewsCategory, MutableList<News>>, responseCode: Int) {
                when(responseCode) {
                    200 -> callback.onResponse(response[category]!!, category)
                    -1 -> callback.onFailure("No network connection")
                    else -> callback.onFailure("Error $responseCode")
                }
            }
        })
    }

    override fun getCatalog(callback: LentaRepositoryUseCase.CallbackCatalog) {
        repository.getNews(object: LentaNetworkRepository.Callback {
            override fun onResponse(response: MutableMap<NewsCategory, MutableList<News>>, responseCode: Int) {
                when(responseCode) {
                    200 -> callback.onResponse(response)
                    -1 -> callback.onFailure("No network connection")
                    else -> callback.onFailure("Error $responseCode")
                }
            }
        })
    }
}