package challenges.cabify.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import challenges.cabify.myapplication.repository.CartRepository
import challenges.cabify.myapplication.model.models.CartItemTotals
import challenges.cabify.myapplication.model.models.CartProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * CartViewModel is a class that extends [ViewModel] and is annotated with [HiltViewModel].
 * This class is used to hold and manage the data for the cart.
 *
 * @param cartRepository is the [CartRepository] instance that provides access to the cart data.
 * @constructor Creates an instance of the class with specified cartRepository.
 *
 *
 * @author Facundo Brandan
 * @version 1.0
 * @since 2023-02-07
 */
@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository): ViewModel() {

    /**
     * [LiveData] property that holds a list of [CartProductItem].
     * The value is retrieved from the [cartRepository].
     */
    val cartProductsLiveData: LiveData<List<CartProductItem>>
    get() = cartRepository.cartProductListLiveData

    /**
     * [LiveData] property that holds a list of [CartItemTotals].
     * The value is retrieved from the [cartRepository].
     */
    val cartTotalsLiveData: LiveData<List<CartItemTotals>>
    get() = cartRepository.cartItemTotalsListLiveData

    /**
     * Function that adds a [CartProductItem] to the cart.
     * @param cartProductItem is the [CartProductItem] to be added to the cart.
     */
    fun addCartProductItem(cartProductItem: CartProductItem) = cartRepository.addCartProductItem(cartProductItem)

    /**
     * Function that removes a [CartProductItem] from the cart.
     * @param cartProductItem is the [CartProductItem] to be removed from the cart.
     */
    fun removeCartProductItem(cartProductItem: CartProductItem) = cartRepository.removeCartProductItem(cartProductItem)

    fun getCartProductList() = cartRepository.cartProductList
}