package com.giftthomu.DtreeTestApp.services

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    //Url of our API
    private const val URL = "https://exercise-646d.restdb.io/rest/"
    private const val KEY = "5c5c7076f210985199db5488"

    //Creating an Interceptor to add API Key
    val headerInterceptor : Interceptor = object : Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            var request : Request = chain.request()
            request = request.newBuilder()
                .addHeader("x-apikey", KEY)
                .build()
            val response : Response = chain.proceed(request)
            return response
        }
    }
    //creating an instance of an HTTp client
    private val okHttp  = OkHttpClient.Builder().addInterceptor(headerInterceptor)

    //Creating a retrofit builder
    private val builder =  Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    ///creating retrofit instance
    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}