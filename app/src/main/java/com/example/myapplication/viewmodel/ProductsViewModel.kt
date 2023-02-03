package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.models.Product
import com.example.myapplication.model.models.ProductsResponse
import com.example.myapplication.model.network.APIService
import com.example.myapplication.model.network.NetworkRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductsViewModel: ViewModel() {
    var liveDataList: MutableLiveData<List<Product>> = MutableLiveData()

    fun getLiveData(): MutableLiveData<List<Product>> {
        return liveDataList
    }

    fun getProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<ProductsResponse> = NetworkRetrofit.getRetrofit()
                .create(APIService::class.java).getProducts("palcalde/6c19259bd32dd6aafa327fa557859c2f/raw/ba51779474a150ee4367cda4f4ffacdcca479887/Products.json")
            val products : ProductsResponse? = call.body()
            if (call.isSuccessful && products != null) {
                liveDataList.postValue(products.productsList)
            } else {
                liveDataList.postValue(emptyList())
            }
        }
    }
}