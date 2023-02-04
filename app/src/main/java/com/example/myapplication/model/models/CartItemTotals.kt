package com.example.myapplication.model.models

data class CartItemTotals(var description: String, var price: Double) {
    companion object {
        fun getCartItemTotalsBasicList(): MutableList<CartItemTotals> {
            val cartItemTotalsSubTotal = CartItemTotals("Subtotal", 0.0)
            val cartItemTotalsDiscounts = CartItemTotals("Discounts", 0.0)
            val cartItemTotalsTotal = CartItemTotals("Total", 0.0)

            return mutableListOf(
                cartItemTotalsSubTotal,
                cartItemTotalsDiscounts,
                cartItemTotalsTotal
            )
        }
    }
}
