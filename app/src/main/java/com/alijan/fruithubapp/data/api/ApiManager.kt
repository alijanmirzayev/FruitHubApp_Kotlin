package com.alijan.fruithubapp.data.api

import com.alijan.fruithubapp.data.model.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiManager {
    @GET("products")
    suspend fun getAllProducts(): Response<List<Product>>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: String): Response<Product>
}