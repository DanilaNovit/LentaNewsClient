package com.novitsky.data.parsers

import com.novitsky.domain.model.NewsModel
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader

class NewsRSSParser {
    private val xpp: XmlPullParser

    init {
        val factory = XmlPullParserFactory.newInstance()
        xpp = factory.newPullParser()
    }

    fun parse(inputString: String?, resultLength: Int = Int.MAX_VALUE): MutableList<NewsModel> {
        if (inputString == null) {
            return mutableListOf()
        }

        xpp.setInput(StringReader(inputString))

        xpp.next()
        var itemCount = 0
        val resultList = mutableListOf<NewsModel>()

        while (xpp.eventType != XmlPullParser.END_DOCUMENT
                && itemCount <= resultLength) {
            if (xpp.eventType == XmlPullParser.START_TAG && xpp.name == "item") {
                ++itemCount

                if (itemCount > resultLength) {
                    continue
                }

                resultList.add(NewsModel())
            }

            if (itemCount == 0) {
                xpp.next()
                continue
            }

            if (xpp.eventType == XmlPullParser.START_TAG) {
                val tagName = xpp.name
                xpp.next()

                when (tagName) {
                    "guid" -> resultList[itemCount - 1].guid = xpp.text
                    "author" -> resultList[itemCount - 1].author = xpp.text
                    "title" -> resultList[itemCount - 1].title = xpp.text
                    "description" -> resultList[itemCount - 1].description = xpp.text
                    "enclosure" -> resultList[itemCount - 1].imageURL =  xpp.getAttributeValue(0)
                }
            }

            xpp.next()
        }

        return resultList
    }
}