package challenges.cabify.myapplication.model.models

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CartItemTotalsTest {
    private lateinit var cartItemVoucher: CartProductItem
    private lateinit var cartItemTShirt: CartProductItem
    private lateinit var cartItemMug: CartProductItem

    @Before
    fun onBefore() {
        cartItemVoucher = CartProductItem(1, ProductItem("VOUCHER", "voucher", 10.0))
        cartItemTShirt = CartProductItem(1,  ProductItem("TSHIRT", "voucher", 20.0))
        cartItemMug = CartProductItem(1,  ProductItem("MUG", "voucher", 7.0))
    }

    @Test
    fun `a cart full of items the updateValuesCartItemTotalsBasicList should return a list with the subtotal, discounts and total with the correct values`() = runBlocking {
        //Given
        cartItemVoucher.itemCount = 11
        cartItemTShirt.itemCount = 7
        cartItemMug.itemCount = 4
        val list = listOf(cartItemMug, cartItemVoucher, cartItemTShirt)

        //When
        val resultList = CartItemTotals.updateValuesCartItemTotalsBasicList(list)

        //Then
        assert(resultList[0].price == 278.0)
        assert(resultList[1].price == 57.0)
        assert(resultList[2].price == 221.0)
    }

    @Test
    fun `a cart empty of items the updateValuesCartItemTotalsBasicList should return a list with the subtotal, discounts and total with values on 0`() = runBlocking {
        //Given
        val list = emptyList<CartProductItem>()

        //When
        val resultList = CartItemTotals.updateValuesCartItemTotalsBasicList(list)

        //Then
        assert(resultList[0].price == 0.0)
        assert(resultList[1].price == 0.0)
        assert(resultList[2].price == 0.0)
    }

}