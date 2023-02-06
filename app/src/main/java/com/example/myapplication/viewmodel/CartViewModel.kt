package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.models.CartItemTotals
import com.example.myapplication.model.models.CartProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class CartViewModel @Inject constructor(): ViewModel() {
    private val _cartProductListLiveData = MutableLiveData<List<CartProductItem>>()
    val cartProductListLiveData: LiveData<List<CartProductItem>>
    get() = _cartProductListLiveData

    private val _cartItemTotalsListLiveData = MutableLiveData<List<CartItemTotals>>()
    val cartItemTotalsListLiveData: LiveData<List<CartItemTotals>>
    get() = _cartItemTotalsListLiveData

    private var cartProductList = mutableListOf<CartProductItem>()

    fun addCartProductItem(cartProductItem: CartProductItem) {
        val index = cartProductList.indexOfFirst { it.productItem == cartProductItem.productItem }
        if (index != -1) {
            cartProductList[index].itemCount ++
        } else {
            cartProductList.add(cartProductItem)
        }
        _cartItemTotalsListLiveData.postValue(
            CartItemTotals.updateValuesCartItemTotalsBasicList(
                cartProductList
            )
        )
        _cartProductListLiveData.postValue(cartProductList)
    }

    fun removeCartProductItem(cartProductItem: CartProductItem) {
        val index = cartProductList.indexOf(cartProductItem)
        if (index != -1) {
            val item = cartProductList[index]
            if (item.itemCount > 1) {
                item.itemCount--
            } else {
                cartProductList.removeAt(index)
            }
            _cartItemTotalsListLiveData.postValue(
                CartItemTotals.updateValuesCartItemTotalsBasicList(
                    cartProductList
                )
            )
            _cartProductListLiveData.postValue(cartProductList)
        }
    }
}