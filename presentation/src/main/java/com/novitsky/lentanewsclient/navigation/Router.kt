package com.novitsky.lentanewsclient.navigation

interface Router {
    fun showCatalog()
    fun showCategory(categoryID: Int)
    fun showNewsDetail(id: String)
    fun back()
}