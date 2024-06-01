package com.alijan.fruithubapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alijan.fruithubapp.data.model.Basket
import com.alijan.fruithubapp.data.model.Product
import com.alijan.fruithubapp.data.source.remote.BaseResponse
import com.alijan.fruithubapp.data.repository.ProductRepository
import com.alijan.fruithubapp.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: ProductRepository) :
    BaseViewModel() {

    private val _product = MutableLiveData<BaseResponse<Product>>()
    val product: LiveData<BaseResponse<Product>> get() = _product

    fun getProductById(id: String) {
        _product.value = BaseResponse.Loading()
        handleApiRequest(
            request = {
                repository.getProductById(id)
            },
            onSuccess = { data ->
                _product.value = BaseResponse.Success(data)
            },
            onError = { errorMessage ->
                errorMessage?.let {
                    _product.value = BaseResponse.Error(errorMessage)
                }
            }
        )
    }

    private val _productBasketCount = MutableLiveData<Int>(1)
    val productBasketCount: LiveData<Int> get() = _productBasketCount

    fun increment() {
        _productBasketCount.value = _productBasketCount.value?.plus(1)
    }

    fun decrement() {
        _productBasketCount.value = _productBasketCount.value?.minus(1)
    }

    fun basketItem(): Basket {
        val product = product.value?.data!!
        val item = Basket(
            productName = product.productName!!,
            productPrice = product.productPrice!!,
            productImage = product.productImage!!,
            id = product.id!!,
            basketCount = productBasketCount.value!!
        )
        return item
    }
}