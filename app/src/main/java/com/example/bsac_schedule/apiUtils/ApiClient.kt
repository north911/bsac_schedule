package com.example.bsac_schedule.apiUtils

interface ApiClient {
    fun getAvailableSchedulesRequest()
    suspend fun makeRequestForHtmlDownloadPage(): Map<String, String>
    suspend fun donloadAndParseGroups(scheduleName: String?): List<String>
}