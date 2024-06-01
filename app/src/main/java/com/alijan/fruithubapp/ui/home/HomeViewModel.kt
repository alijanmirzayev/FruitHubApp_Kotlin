package com.alijan.fruithubapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alijan.fruithubapp.data.model.Basket
import com.alijan.fruithubapp.data.model.Product
import com.alijan.fruithubapp.data.repository.ProductRepository
import com.alijan.fruithubapp.data.source.remote.BaseResponse
import com.alijan.fruithubapp.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ProductRepository) :
    BaseViewModel() {

    private val _products = MutableLiveData<BaseResponse<List<Product>>>()
    val products: LiveData<BaseResponse<List<Product>>> get() = _products

    private val _basket = MutableLiveData<MutableList<Basket>>()
    val basket: LiveData<MutableList<Basket>> get() = _basket

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        _products.value = BaseResponse.Loading()
        handleApiRequest(
            request = {
                repository.getAllProducts()
            },
            onSuccess = { data ->
                _products.value = BaseResponse.Success(data)
            },
            onError = { errorMessage ->
                _products.value = BaseResponse.Error(errorMessage)
            }
        )
    }

    fun addItemToBasket(item: Basket) {
        val currentBasket = _basket.value ?: ArrayList()
        currentBasket.add(item)
        _basket.value = currentBasket
    }

}