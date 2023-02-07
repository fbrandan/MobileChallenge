package challenges.cabify.myapplication.model.models

import java.lang.Double.max
import javax.inject.Inject

data class CartItemTotals @Inject constructor(var description: String, var price: Double) {
    companion object {
        private fun getCartSubTotal(cartProductList: List<CartProductItem>): Double =
            cartProductList.sumOf { cartItem -> cartItem.itemCount * cartItem.productItem.price }

        private fun getCartDiscounts(cartProductList: List<CartProductItem>): Double {
            val voucherDiscount = cartProductList
                .filter { it.productItem.code == "VOUCHER" && it.itemCount >= 2 }
                .sumOf {
                    val applyTwoForOne = it.itemCount / 2 + it.itemCount % 2
                    (it.itemCount - applyTwoForOne) * it.productItem.price
                }
            val tShirtDiscount = cartProductList
                .filter { it.productItem.code == "TSHIRT" && it.itemCount >= 3 }
                .sumOf {
                    it.itemCount * (it.productItem.price - 19.00)
                }
            return voucherDiscount + tShirtDiscount
        }

        fun updateValuesCartItemTotalsBasicList(cartProductList: List<CartProductItem>): List<CartItemTotals> {
            val subTotal = getCartSubTotal(cartProductList)
            val discounts = getCartDiscounts(cartProductList)
            val total = max(0.0, subTotal - discounts)
            return listOf(
                CartItemTotals("Subtotal", subTotal),
                CartItemTotals("Discounts", discounts),
                CartItemTotals("Total", total)
            )
        }
    }
}
