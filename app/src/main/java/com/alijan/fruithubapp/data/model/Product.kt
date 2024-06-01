package com.alijan.fruithubapp.data.model


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: String?,
    @SerializedName("productContains")
    val productContains: String?,
    @SerializedName("productDescription")
    val productDescription: String?,
    @SerializedName("productImage")
    val productImage: String?,
    @SerializedName("productName")
    val productName: String?,
    @SerializedName("productPrice")
    val productPrice: String?
)