package challenges.cabify.myapplication.model.models

import challenges.cabify.myapplication.Constants.KEY_CART_PRODUCT_ITEM_COUNT_DEFAULT

/**
 * Class to store the information about a cart item including the number of items and the product information.
 * @property itemCount The number of items in the cart for this product. Default is 0.
 * @property productItem The product information for the item in the cart.
 *
 * @author Facundo Brandan
 * @version 1.0
 * @since 2023-02-07
 */
data class CartProductItem(var itemCount: Int = KEY_CART_PRODUCT_ITEM_COUNT_DEFAULT, val productItem: ProductItem)