package challenges.cabify.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import challenges.cabify.myapplication.model.models.ProductItem
import challenges.cabify.myapplication.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ProductsViewModel is a class that extends [ViewModel] and is annotated with [HiltViewModel].
 * This class is used to hold and manage the data for the products.
 *
 * @param productRepository is the [ProductRepository] instance that provides access to the product data.
 * @constructor Creates an instance of the class with specified productRepository.
 */
@HiltViewModel
class ProductsViewModel @Inject constructor(private val productRepository: ProductRepository): ViewModel() {

    /**
     * [LiveData] property that holds a list of [ProductItem].
     * The value is retrieved from the [productRepository].
     */
    val productsLiveData : LiveData<List<ProductItem>>
    get() =  productRepository.productList

    /**
     * Initialization block that launches a coroutine in IO Dispatcher and retrieves the products using [productRepository].
     */
    init {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.getProducts()
        }
    }
}