package challenges.cabify.myapplication.viewmodel

import challenges.cabify.myapplication.model.models.CartProductItem
import challenges.cabify.myapplication.model.models.ProductItem
import challenges.cabify.myapplication.repository.CartRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CartViewModelTest {

    @RelaxedMockK
    private lateinit var cartRepository: CartRepository

    lateinit var cartViewModel: CartViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        cartViewModel = CartViewModel(cartRepository)
    }

    @Test
    fun `when you add a product item from the viewmodel, the same method should be called in the repository`() =
        runBlocking {
            val cartProductItem = CartProductItem(1, ProductItem("code", "name", 0.00))

            //When
            cartViewModel.addCartProductItem(cartProductItem)

            //Then
            coVerify { cartRepository.addCartProductItem(cartProductItem) }
        }

    @Test
    fun `when you remove a product item from the viewmodel, the same method should be called in the repository`() =
        runBlocking {
            val cartProductItem = CartProductItem(1, ProductItem("code", "name", 0.00))
            cartViewModel.addCartProductItem(cartProductItem)

            //When
            cartViewModel.removeCartProductItem(cartProductItem)

            //Then
            coVerify { cartRepository.removeCartProductItem(cartProductItem) }
        }

    @Test
    fun `when you get the cartProductsLiveData from view model, the same method should be called in the repository`() =
        runBlocking {

            //When
            cartViewModel.cartProductsLiveData

            //Then
            coVerify { cartRepository.cartProductListLiveData }
        }

    @Test
    fun `when you get the cartTotalsLiveData from view model, the same method should be called in the repository`() =
        runBlocking {

            //When
            cartViewModel.cartTotalsLiveData

            //Then
            coVerify { cartRepository.cartItemTotalsListLiveData }
        }

    @Test
    fun `when you get the cartProductsLiveData from view model the result should not be null`() =
        runBlocking {

            //When
            val liveData = cartViewModel.cartProductsLiveData

            //Then
            assert(liveData != null)
        }

    @Test
    fun `when you get the cartTotalsLiveData from view model the result should not be null`() = runBlocking {

        //When
        val liveData = cartViewModel.cartTotalsLiveData

        //Then
        assert(liveData != null)
    }
}