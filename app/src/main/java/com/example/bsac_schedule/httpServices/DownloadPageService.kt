package com.example.bsac_schedule.httpServices

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface DownloadPageService {

    @GET("raspisania-grupp-dnevnoi-formy-obucenia")
    fun getTimetablesHtmlPage(): Deferred<Response<String>>
}