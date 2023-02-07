package challenges.cabify.myapplication.model.models

import challenges.cabify.myapplication.Constants.*
import java.lang.Double.max
import javax.inject.Inject

/**
 * CartItemTotals represents the totals of a cart item, including the description and price.
 *
 * @author Facundo Brandan
 * @version 1.0
 * @since 2023-02-07
 */
data class CartItemTotals @Inject constructor(
    /**
     * The description of the total, e.g. "Subtotal", "Discounts", or "Total".
     */
    var description: String,
    /**
     * The price of the total.
     */
    var price: Double) {
    companion object {

        /**
         * Calculates the subtotal of the cart products.
         *
         * @param cartProductList a list of CartProductItem.
         * @return the subtotal of the cart products.
         */
        private fun getCartSubTotal(cartProductList: List<CartProductItem>): Double =
            cartProductList.sumOf { cartItem -> cartItem.itemCount * cartItem.productItem.price }

        /**
         * Calculates the discounts of the cart products.
         *
         * @param cartProductList a list of CartProductItem.
         * @return the discounts of the cart products.
         */
        private fun getCartDiscounts(cartProductList: List<CartProductItem>): Double {
            val voucherDiscount = cartProductList
                .filter { it.productItem.code == KEY_CART_VOUCHER && it.itemCount >= 2 }
                .sumOf {
                    val applyTwoForOne = it.itemCount / 2 + it.itemCount % 2
                    (it.itemCount - applyTwoForOne) * it.productItem.price
                }
            val tShirtDiscount = cartProductList
                .filter { it.productItem.code == KEY_CART_TSHIRT && it.itemCount >= 3 }
                .sumOf {
                    it.itemCount * (it.productItem.price - 19.00)
                }
            return voucherDiscount + tShirtDiscount
        }

        /**
         * Updates the values of the cart item totals.
         *
         * @param cartProductList a list of CartProductItem.
         * @return a list of CartItemTotals.
         */
        fun updateValuesCartItemTotalsBasicList(cartProductList: List<CartProductItem>): List<CartItemTotals> {
            val subTotal = getCartSubTotal(cartProductList)
            val discounts = getCartDiscounts(cartProductList)
            val total = max(0.0, subTotal - discounts)
            return listOf(
                CartItemTotals(KEY_CART_SUBTOTALS, subTotal),
                CartItemTotals(KEY_CART_DISCOUNTS, discounts),
                CartItemTotals(KEY_CART_TOTALS, total)
            )
        }
    }
}
