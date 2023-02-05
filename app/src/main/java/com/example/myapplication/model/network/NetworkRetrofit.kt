package com.example.myapplication.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkRetrofit {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}