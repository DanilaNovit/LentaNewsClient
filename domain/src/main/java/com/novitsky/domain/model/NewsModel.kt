package com.novitsky.domain.model

data class NewsModel(
    var guid: String? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var imageURL: String? = null
)
