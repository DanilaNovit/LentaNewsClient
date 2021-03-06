package com.novitsky.data.parsers

import com.novitsky.data.mappers.NewsMapper
import com.novitsky.domain.model.News
import com.novitsky.domain.model.NewsCategoryModel
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader

class NewsRSSParser {
    private val xpp: XmlPullParser

    init {
        val factory = XmlPullParserFactory.newInstance()
        xpp = factory.newPullParser()
    }

    fun parse(inputString: String?, container: MutableList<News>,
              resultLength: Int = Int.MAX_VALUE): MutableList<News> {
        if (inputString == null) {
            return mutableListOf()
        }

        xpp.setInput(StringReader(inputString))

        xpp.next()
        var itemCount = 0

        var tmpGuid = ""
        var tmpTitle = ""
        var tmpDescription = ""
        var tmpImageURL = ""
        val mapper = NewsMapper()
        var categoryModel: NewsCategoryModel? = null

        while (xpp.eventType != XmlPullParser.END_DOCUMENT
                && itemCount <= resultLength) {
            if (xpp.eventType == XmlPullParser.START_TAG && xpp.name == "item") {
                ++itemCount

                if (itemCount > resultLength) {
                    continue
                }
            }

            if (itemCount == 0) {
                xpp.next()
                continue
            }

            if (xpp.eventType == XmlPullParser.START_TAG) {
                val tagName = xpp.name
                xpp.next()

                when (tagName) {
                    "guid" -> tmpGuid = xpp.text
                    "title" -> tmpTitle = xpp.text
                    "description" -> tmpDescription = xpp.text
                    "enclosure" -> tmpImageURL = xpp.getAttributeValue(0)
                    "category" -> {
                        if (categoryModel == null) {
                            categoryModel = mapper.getCategoryModelByTitle(xpp.text)
                        }

                        if (itemCount < container.size) {
                            container[itemCount - 1].ID = tmpGuid
                            container[itemCount - 1].detailsUrl = tmpGuid
                            container[itemCount - 1].title = tmpTitle
                            container[itemCount - 1].description = tmpDescription
                            container[itemCount - 1].imageURL = tmpImageURL
                            container[itemCount - 1].category = categoryModel
                        } else {
                            container.add(News(tmpGuid, tmpGuid,tmpTitle, tmpDescription,
                                    tmpImageURL, categoryModel))
                        }
                    }
                }
            }

            xpp.next()
        }

        return container
    }
}
