package com.novitsky.domain.useсases

import com.novitsky.domain.model.News
import com.novitsky.domain.repository.LentaNetworkRepository

class GetCatalogUseCaseImpl(private val repository: LentaNetworkRepository): GetCatalogUseCase {
    override fun get(callback: GetCatalogUseCase.Callback) {
        repository.getNews(object: LentaNetworkRepository.Callback {
            override fun onResponse(response: MutableMap<Int, MutableList<News>>, responseCode: Int) {
                when(responseCode) {
                    200 -> callback.onResponse(response)
                    -1 -> callback.onFailure("No network connection")
                    else -> callback.onFailure("Error $responseCode")
                }
            }
        })
    }
}
