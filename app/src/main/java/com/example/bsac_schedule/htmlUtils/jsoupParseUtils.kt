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

fun getMapOfValues(str: String?): Map<String, String> {
    var elements = getElements(convertToDoc(str))
    var map: MutableMap<String, String> = HashMap()
    elements?.forEach { element ->
        map.put(element.text(), validateUrlString(element.attr("href")))
    }
    return map
}

private fun validateUrlString(str: String): String {
    if (!str.contains("bsac.by")) {
        return "http://bsac.by/".plus(str)
    }
    return str
}