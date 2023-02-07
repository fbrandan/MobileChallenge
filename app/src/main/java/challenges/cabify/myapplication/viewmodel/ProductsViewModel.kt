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

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productRepository: ProductRepository): ViewModel() {
    val productsLiveData : LiveData<List<ProductItem>>
    get() =  productRepository.productList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.getProducts()
        }
    }
}