package com.alijan.fruithubapp.data.repository

import com.alijan.fruithubapp.data.model.Product
import com.alijan.fruithubapp.data.source.remote.ApiManager
import com.alijan.fruithubapp.data.source.remote.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ProductRepository @Inject constructor(private val apiManager: ApiManager) {

    suspend fun getAllProducts (): Response<List<Product>> = withContext(Dispatchers.IO){
        return@withContext apiManager.getAllProducts()
    }

    suspend fun getProductById (id: String): Response<Product> = withContext(Dispatchers.IO){
        return@withContext apiManager.getProductById(id)
    }
}