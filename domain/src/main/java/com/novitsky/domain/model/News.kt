package com.novitsky.domain.model

data class News(
    var guid: String,
    var title: String,
    var description: String,
    var imageURL: String,
    var category: String
)
