package com.example.bsac_schedule.htmlUtils

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


fun convertToDoc(str: String?): Document? {
    return if (str != null)
        Jsoup.parse(str)
    else
        null
}

fun getElements(document: Document?): Elements? {
    return document?.getElementsByClass(
        "clearfix text-formatted field field--name-body field--type-text-with-summary field--label-hidden field__item"
    )?.select("a")
}

fun getStringArrayOfValues(str: String?): Array<String> {
    var elements = getElements(convertToDoc(str))
    var array: MutableList<String> = ArrayList()
    elements?.forEach {element ->
        array.add(element.text())
    }
    return array.toTypedArray()
}