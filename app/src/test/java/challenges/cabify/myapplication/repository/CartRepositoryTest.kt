package challenges.cabify.myapplication.repository

import challenges.cabify.myapplication.model.models.CartProductItem
import challenges.cabify.myapplication.model.models.ProductItem
import io.mockk.MockKAnnotations
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CartRepositoryTest {

    lateinit var cartRepository: CartRepository

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        cartRepository = CartRepository()
    }

    @Test
    fun `when the cartProductListLiveData is called should not be null`() = runBlocking {
        //When
        val liveData = cartRepository.cartProductListLiveData

        //Then
        assert(liveData != null)
    }

    @Test
    fun `when the cartItemTotalsListLiveData is called should not be null`() = runBlocking {
        //When
        val liveData = cartRepository.cartItemTotalsListLiveData

        //Then
        assert(liveData != null)
    }

    @Test
    fun `when addCartProductItem is called the size of the cartProductList should increase` () = runBlocking {
        val cartProductItem = CartProductItem(1, ProductItem("code", "name", 0.0))
        //When
        cartRepository.addCartProductItem(cartProductItem)

        //Then
        assert ( cartRepository.cartProductList.size == 1 )
    }

    @Test
    fun `when removeCartProductItem is called the size of the cartProductList should decrease` () = runBlocking {
        val cartProductItem = CartProductItem(1, ProductItem("code", "name", 0.0))
        cartRepository.addCartProductItem(cartProductItem)

        //When
        cartRepository.removeCartProductItem(cartProductItem)

        //Then
        assert ( cartRepository.cartProductList.size == 0 )
    }
}