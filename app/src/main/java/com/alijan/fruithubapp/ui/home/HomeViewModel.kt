package com.alijan.fruithubapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alijan.fruithubapp.data.model.Basket
import com.alijan.fruithubapp.data.model.Product
import com.alijan.fruithubapp.data.repository.ProductRepository
import com.alijan.fruithubapp.data.api.BaseResponse
import com.alijan.fruithubapp.data.repository.DataStoreRepository
import com.alijan.fruithubapp.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ProductRepository, private val dataStoreRepository: DataStoreRepository) :
    BaseViewModel() {

    private val _products = MutableLiveData<BaseResponse<List<Product>>>()
    val products: LiveData<BaseResponse<List<Product>>> get() = _products

    private val _basket = MutableLiveData<MutableList<Basket>>()
    val basket: LiveData<MutableList<Basket>> get() = _basket

    private val _name = MutableLiveData<String?>()
    val name: LiveData<String?> get() = _name

    init {
        getAllProducts()
        getName()
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

    private fun getName() {
        viewModelScope.launch {
            try {
                val name = dataStoreRepository.getName()

                if(name != null) {
                    _name.value = name
                }else {
                    _name.value = ""
                }

            } catch (e: Exception){
                Log.e("AuthenticationViewModel", e.localizedMessage.toString())
            }
        }
    }

    fun addItemToBasket(item: Basket) {
        val currentBasket = _basket.value ?: ArrayList()
        currentBasket.add(item)
        _basket.value = currentBasket
    }

}