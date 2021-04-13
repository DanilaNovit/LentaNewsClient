package com.novitsky.lentanewsclient.navigation

import com.novitsky.domain.model.NewsModel
import com.novitsky.domain.repository.LentaNetworkRepository
import com.novitsky.lentanewsclient.viewholders.CatalogViewHolderFactory
import com.novitsky.lentanewsclient.viewholders.CategoryViewHolder

interface Router {
    fun showCatalog(newsMap: MutableMap<LentaNetworkRepository.NewsCategory, MutableList<NewsModel>>,
                    listener: CatalogViewHolderFactory.OnItemCatalogClickListener)
    fun showCategory(newsList: MutableList<NewsModel>,
                     listener: CategoryViewHolder.OnCategoryClickListener)
    fun showDetail(url: String)
    fun back()
    fun isRoot(): Boolean
}