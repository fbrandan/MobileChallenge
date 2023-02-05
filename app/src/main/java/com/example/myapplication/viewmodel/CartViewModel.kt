package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.models.CartItemTotals
import com.example.myapplication.model.models.CartItemTotals.Companion.updateValuesCartItemTotalsBasicList
import com.example.myapplication.model.models.CartProductItem
import com.example.myapplication.model.models.ProductItem

class CartViewModel: ViewModel() {
    companion object {
        private var liveDataCartProductList: MutableLiveData<List<CartProductItem>> = MutableLiveData()
        private var liveDataCartTotals: MutableLiveData<List<CartItemTotals>> = MutableLiveData()

        private var cartProductList = mutableListOf<CartProductItem>()

        fun getLiveDataCartProductList(): MutableLiveData<List<CartProductItem>> {
            return liveDataCartProductList
        }

        fun getLiveDataCartTotals(): MutableLiveData<List<CartItemTotals>> {
            return liveDataCartTotals
        }

        fun addCartProductItem(cartProductItem: CartProductItem) {
            val index = cartProductList.indexOfFirst { it.productItem == cartProductItem.productItem }
            if (index != -1) {
                cartProductList[index].itemCount ++
            } else {
                cartProductList.add(cartProductItem)
            }
            liveDataCartTotals.postValue(updateValuesCartItemTotalsBasicList(cartProductList))
            liveDataCartProductList.postValue(cartProductList)
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
                liveDataCartTotals.postValue(updateValuesCartItemTotalsBasicList(cartProductList))
                liveDataCartProductList.postValue(cartProductList)
            }
        }
    }
}