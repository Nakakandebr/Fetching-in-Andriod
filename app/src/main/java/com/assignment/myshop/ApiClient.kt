package com.assignment.myshop

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiClient {
    var  retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <B> buildClient(apiInterface: Class<B>):B{
        return retrofit.create(apiInterface)
    }
}