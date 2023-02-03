package com.example.myapplication.model.network

import com.example.myapplication.model.models.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getProducts(@Url pathUrl: String):Response<ProductsResponse>
}