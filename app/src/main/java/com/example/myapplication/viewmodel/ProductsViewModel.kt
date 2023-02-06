package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Repository.ProductRepository
import com.example.myapplication.model.models.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val repository: ProductRepository): ViewModel() {
    val productsLiveData : LiveData<List<ProductItem>>
    get() =  repository.productList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProducts()
        }
    }
}