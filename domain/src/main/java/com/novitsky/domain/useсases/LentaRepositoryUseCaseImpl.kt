package com.novitsky.domain.useсases

import com.novitsky.domain.model.News
import com.novitsky.domain.model.NewsCategory
import com.novitsky.domain.repository.LentaNetworkRepository

class LentaRepositoryUseCaseImpl(
        private val repository: LentaNetworkRepository
): LentaRepositoryUseCase {
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

    override fun getRussianCategory(callback: LentaRepositoryUseCase.CallbackCategory) {
        getCategory(NewsCategory.RUSSIA, callback)
    }

    override fun getWorldCategory(callback: LentaRepositoryUseCase.CallbackCategory) {
        getCategory(NewsCategory.WORLD, callback)
    }

    override fun getUssrCategory(callback: LentaRepositoryUseCase.CallbackCategory) {
        getCategory(NewsCategory.USSR, callback)
    }

    override fun getEconomicsCategory(callback: LentaRepositoryUseCase.CallbackCategory) {
        getCategory(NewsCategory.ECONOMICS, callback)
    }

    override fun getScienceCategory(callback: LentaRepositoryUseCase.CallbackCategory) {
        getCategory(NewsCategory.SCIENCE, callback)
    }

    override fun getCultureCategory(callback: LentaRepositoryUseCase.CallbackCategory) {
        getCategory(NewsCategory.CULTURE, callback)
    }

    override fun getSportCategory(callback: LentaRepositoryUseCase.CallbackCategory) {
        getCategory(NewsCategory.SPORT, callback)
    }

    override fun getMediaCategory(callback: LentaRepositoryUseCase.CallbackCategory) {
        getCategory(NewsCategory.MEDIA, callback)
    }

    override fun getStyleCategory(callback: LentaRepositoryUseCase.CallbackCategory) {
        getCategory(NewsCategory.STYLE, callback)
    }

    override fun getTravelCategory(callback: LentaRepositoryUseCase.CallbackCategory) {
        getCategory(NewsCategory.TRAVEL, callback)
    }

    override fun getLifeCategory(callback: LentaRepositoryUseCase.CallbackCategory) {
        getCategory(NewsCategory.LIFE, callback)
    }

    override fun getRealtyCategory(callback: LentaRepositoryUseCase.CallbackCategory) {
        getCategory(NewsCategory.REALTY, callback)
    }

    private fun getCategory(category: NewsCategory,
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
}