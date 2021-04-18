package com.novitsky.lentanewsclient.navigation

import com.novitsky.domain.model.NewsCategory

interface Router {
    fun showCatalog()
    fun showCategory(category: NewsCategory)
    fun showDetail(url: String)
    fun back()
}