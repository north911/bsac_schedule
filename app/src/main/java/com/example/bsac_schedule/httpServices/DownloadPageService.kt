package com.example.bsac_schedule.httpServices

import retrofit2.Call
import retrofit2.http.GET

interface DownloadPageService {

    @GET("raspisania-grupp-dnevnoi-formy-obucenia")
    fun get() : Call<String>
}