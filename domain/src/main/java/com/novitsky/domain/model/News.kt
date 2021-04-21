package com.novitsky.domain.model

data class News(
    var ID: String,
    var detailsUrl: String,
    var title: String,
    var description: String,
    var imageURL: String,
    var category: NewsCategoryModel
)
