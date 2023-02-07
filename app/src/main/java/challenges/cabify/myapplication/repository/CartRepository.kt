package challenges.cabify.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import challenges.cabify.myapplication.model.models.CartItemTotals
import challenges.cabify.myapplication.model.models.CartProductItem
import javax.inject.Inject
import javax.inject.Singleton

/**
 * CartRepository is a class responsible for managing the data of the shopping cart.
 *
 * @property _cartProductListLiveData is a MutableLiveData containing the list of products in the shopping cart.
 * @property cartProductListLiveData is a LiveData that exposes the _cartProductListLiveData.
 * @property _cartItemTotalsListLiveData is a MutableLiveData containing the list of totals of the shopping cart, such as subtotal, discounts, and total.
 * @property cartItemTotalsListLiveData is a LiveData that exposes the _cartItemTotalsListLiveData.
 * @property cartProductList is a mutable list of CartProductItem objects representing the products in the shopping cart.
 *
 * The class provides two main methods:
 * - addCartProductItem.
 * - removeCartProductItem.
 *
 * @author Facundo Brandan
 * @version 1.0
 * @since 2023-02-07
 */
@Singleton
class CartRepository @Inject constructor() {
    private val _cartProductListLiveData = MutableLiveData<List<CartProductItem>>()
    val cartProductListLiveData: LiveData<List<CartProductItem>>
        get() = _cartProductListLiveData

    private val _cartItemTotalsListLiveData = MutableLiveData<List<CartItemTotals>>()
    val cartItemTotalsListLiveData: LiveData<List<CartItemTotals>>
        get() = _cartItemTotalsListLiveData

    var cartProductList: MutableList<CartProductItem> = mutableListOf()

    /**
     * Adds a product to the shopping cart. If the product already exists in the cart, increases its quantity by one.
     *
     * @param cartProductItem is a CartProductItem instance.
     */
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

    /**
     * Removes a product from the shopping cart. If the product quantity is greater than one, decreases its quantity by one.
     *
     * @param cartProductItem is a CartProductItem instance.
     */
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