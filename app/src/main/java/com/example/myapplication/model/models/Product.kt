package com.example.myapplication.model.models

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("code") var code: String,
    @SerializedName("name") var name: String,
    @SerializedName("price") var price: Double) {
        override fun toString(): String {
            return "Product(code='$code', name='$name', price=$price)\n"
    }
}
