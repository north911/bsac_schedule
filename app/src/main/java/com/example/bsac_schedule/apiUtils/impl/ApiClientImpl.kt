package com.example.bsac_schedule.apiUtils.impl

import com.example.bsac_schedule.apiUtils.ApiClient
import com.example.bsac_schedule.fileDownloader.FileDownloader
import com.example.bsac_schedule.htmlUtils.getMapOfValues
import com.example.bsac_schedule.httpServices.DownloadPageService
import com.example.bsac_schedule.httpServices.buildUri

class ApiClientImpl : ApiClient {
    override fun getAvailableSchedulesRequest() {
        TODO("Not yet implemented")
    }

    override suspend fun makeRequestForHtmlDownloadPage(): Map<String, String> {
        val requestAddress: DownloadPageService = buildUri()
        return getMapOfValues(
            requestAddress
                .getTimetablesHtmlPage()
                .await()
                .body()
        )
    }

    override suspend fun donloadAndParseGroups(scheduleName: String?): List<String> {
        return if (scheduleName != null)
            FileDownloader().downloadFile(scheduleName)
        else
            listOf("no schedule")
    }
}