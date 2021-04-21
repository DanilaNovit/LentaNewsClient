package com.novitsky.data.mappers

import com.novitsky.domain.model.NewsCategory
import com.novitsky.domain.model.NewsCategoryModel
import java.lang.Exception

class NewsMapper {
    private fun getCategoryTypeByTitle(title: String): NewsCategory {
        return when(title) {
            "Россия" -> NewsCategory.RUSSIA
            "Мир" -> NewsCategory.WORLD
            "Бывший СССР" -> NewsCategory.USSR
            "Экономика" -> NewsCategory.ECONOMICS
            "Наука и техника" -> NewsCategory.SCIENCE
            "Культура" -> NewsCategory.CULTURE
            "Спорт" -> NewsCategory.SPORT
            "Интернет и СМИ" -> NewsCategory.MEDIA
            "Ценности" -> NewsCategory.STYLE
            "Путешествия" -> NewsCategory.TRAVEL
            "Из жизни" -> NewsCategory.LIFE
            "Дом" -> NewsCategory.REALTY
            else -> throw Exception("Unknown title")
        }
    }

    fun getUrlKeyByCategoryNews(category: NewsCategory): String {
        return when(category) {
            NewsCategory.RUSSIA -> "russia"
            NewsCategory.WORLD -> "world"
            NewsCategory.USSR -> "ussr"
            NewsCategory.ECONOMICS -> "economics"
            NewsCategory.SCIENCE -> "science"
            NewsCategory.CULTURE -> "culture"
            NewsCategory.SPORT -> "sport"
            NewsCategory.MEDIA -> "media"
            NewsCategory.STYLE -> "style"
            NewsCategory.TRAVEL -> "travel"
            NewsCategory.LIFE -> "life"
            NewsCategory.REALTY -> "realty"
        }
    }

    fun getIdByCategoryNews(category: NewsCategory): Int {
        return category.hashCode()
    }

    fun getCategoryModelByTitle(title: String): NewsCategoryModel {
        val type = getCategoryTypeByTitle(title)

        return NewsCategoryModel(
                getIdByCategoryNews(type),
                title,
                type
        )
    }
}
