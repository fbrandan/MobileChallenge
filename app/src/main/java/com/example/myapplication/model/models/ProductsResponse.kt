package com.example.myapplication.model.models

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("products") var productsList: List<Product>)
