package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.models.CartItemTotals
import com.example.myapplication.model.models.CartProductItem
import com.example.myapplication.model.models.ProductItem

class CartViewModel: ViewModel() {
    companion object {
        private var liveDataCartProductList: MutableLiveData<List<CartProductItem>> = MutableLiveData()
        private var liveDataCartTotals: MutableLiveData<List<CartItemTotals>> = MutableLiveData()

        private var cartProductList = mutableListOf<CartProductItem>()
        private var cartTotalsList = mutableListOf<CartItemTotals>()

        fun getLiveDataCartProductList(): MutableLiveData<List<CartProductItem>> {
            return liveDataCartProductList
        }

        fun getLiveDataCartTotals(): MutableLiveData<List<CartItemTotals>> {
            return liveDataCartTotals
        }

        fun addCartProductItem(cartProductItem: CartProductItem) {
            if (!isCardProductInList(cartProductItem.productItem)) {
                cartProductList.add(cartProductItem)
            } else {
                val index = cartProductList.indexOfFirst { it.productItem == cartProductItem.productItem }
                cartProductList[index].itemCount ++
            }
            liveDataCartProductList.postValue(cartProductList)
        }

        fun addCartTotalsItem(cartItemTotals: CartItemTotals) {
            if (!cartTotalsList.contains(cartItemTotals)) {
                cartTotalsList.add(cartItemTotals)
                liveDataCartTotals.postValue(cartTotalsList)
            }
        }

        fun removeCartProductItem(cartProductItem: CartProductItem) {
            if (isCardProductInList(cartProductItem.productItem)) {
                val index = cartProductList.indexOf(cartProductItem)

                if (cartProductList[index].itemCount <= 1) {
                    cartProductList.removeAt(index)
                } else {
                    cartProductList[index].itemCount--
                }
                liveDataCartProductList.postValue(cartProductList)
            }
        }

        private fun isCardProductInList(productItem: ProductItem) : Boolean {
            return cartProductList.any { it.productItem == productItem }
        }
    }
}