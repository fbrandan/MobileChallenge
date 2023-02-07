package challenges.cabify.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import challenges.cabify.myapplication.repository.CartRepository
import challenges.cabify.myapplication.model.models.CartItemTotals
import challenges.cabify.myapplication.model.models.CartProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository): ViewModel() {
    val cartProductsLiveData: LiveData<List<CartProductItem>>
    get() = cartRepository.cartProductListLiveData

    val cartTotalsLiveData: LiveData<List<CartItemTotals>>
    get() = cartRepository.cartItemTotalsListLiveData

    fun addCartProductItem(cartProductItem: CartProductItem) = cartRepository.addCartProductItem(cartProductItem)

    fun removeCartProductItem(cartProductItem: CartProductItem) = cartRepository.removeCartProductItem(cartProductItem)
}