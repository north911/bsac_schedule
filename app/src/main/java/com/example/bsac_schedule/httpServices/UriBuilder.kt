package com.example.bsac_schedule.httpServices

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


fun buildUri() : DownloadPageService {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://bsac.by/pages/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    return retrofit.create(DownloadPageService::class.java)
}