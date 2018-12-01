package com.telefonica.lucferbux.isecurityapp.networking

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DataRetriever {

    val service: RetrieveService

    companion object {
        const val BASE_URL = "http://ec2-35-178-182-30.eu-west-2.compute.amazonaws.com:5000"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        service = retrofit.create(RetrieveService::class.java)
    }

}