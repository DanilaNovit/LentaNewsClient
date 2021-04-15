package com.novitsky.domain.useсases

import com.novitsky.domain.model.News
import com.novitsky.domain.repository.LentaNetworkRepository

class LentaRepositoryUseCaseImpl(private val repository: LentaNetworkRepository): LentaRepositoryUseCase {
    override fun getCategory(category: LentaNetworkRepository.NewsCategory,
                             callback: LentaRepositoryUseCase.CallbackCategory) {
        repository.getNews(category, object: LentaNetworkRepository.Callback {
            override fun onResponse(response: MutableList<News>, responseCode: Int) {
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
        val resultMap = mutableMapOf<LentaNetworkRepository.NewsCategory,
                MutableList<News>>()

        var containerIterateIndex = 0

        val categories = LentaNetworkRepository.NewsCategory.values()
        categories.forEach {
            val categoryList = getCategoryInCatalogList(containerIterateIndex,
                    numberOfNewsInCategory)
            containerIterateIndex += numberOfNewsInCategory

            repository.getNews(it, object: LentaNetworkRepository.Callback {
                override fun onResponse(response: MutableList<News>, responseCode: Int) {
                    if (responseCode == 200) {
                        ++uploadedCategories
                        resultMap[it] = response
                        saveNewsList(response)

                        if (uploadedCategories == categories.size) {
                            callback.onResponse(resultMap)
                        }
                    } else {
                        when (responseCode) {
                            -1   -> callback.onFailure("No network connection")
                            else -> callback.onFailure("Error $responseCode")
                        }
                    }
                }
            }, categoryList, numberOfNewsInCategory)
        }
    }

    private fun getCategoryInCatalogList(containerIterateIndex: Int,
                                         numberOfNewsInCategory: Int): MutableList<News> {
        val categoryList = mutableListOf<News>()

        for (i in containerIterateIndex..containerIterateIndex + numberOfNewsInCategory) {
            if(newsContainer.size > i) {
                categoryList.add(newsContainer[i])
            }
        }

        return categoryList
    }

    private fun saveNewsList(newsList: MutableList<News>) {
        for (i in newsList) {
            if (!newsContainer.contains(i)) {
                newsContainer.add(i)
            }
        }
    }

    companion object {
        private val newsContainer = mutableListOf<News>()
    }
}