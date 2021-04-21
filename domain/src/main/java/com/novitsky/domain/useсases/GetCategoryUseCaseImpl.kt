package com.novitsky.domain.useсases

import com.novitsky.domain.model.News
import com.novitsky.domain.repository.LentaNetworkRepository

class GetCategoryUseCaseImpl(private val repository: LentaNetworkRepository): GetCategoryUseCase {
    override fun get(categoryID: Int, callback: GetCategoryUseCase.Callback) {
        repository.getNews(object: LentaNetworkRepository.Callback {
            override fun onResponse(response: MutableMap<Int, MutableList<News>>, responseCode: Int) {
                when(responseCode) {
                    200 -> callback.onResponse(response[categoryID]!!)
                    -1 -> callback.onFailure("No network connection")
                    else -> callback.onFailure("Error $responseCode")
                }
            }
        })
    }
}
