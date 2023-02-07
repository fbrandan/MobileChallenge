package challenges.cabify.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import challenges.cabify.myapplication.model.models.ProductItem
import challenges.cabify.myapplication.model.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductRepository @Inject constructor(private val apiService: APIService) {
    private val _productsList = MutableLiveData<List<ProductItem>>()
    val productList: LiveData<List<ProductItem>>
    get() = _productsList

    fun getProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            val result =
                apiService.getProducts("palcalde/6c19259bd32dd6aafa327fa557859c2f/raw/ba51779474a150ee4367cda4f4ffacdcca479887/Products.json")
            if (result.isSuccessful && result.body() != null) {
                _productsList.postValue(result.body()!!.productsList)
            } else {
                _productsList.postValue(emptyList())
            }
        }
    }
}