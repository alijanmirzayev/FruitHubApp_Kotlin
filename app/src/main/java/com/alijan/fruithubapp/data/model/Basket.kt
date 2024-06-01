package com.alijan.fruithubapp.data.model

data class Basket(
    val productName: String,
    val productPrice: String,
    val productImage: String,
    val id: String,
    val basketCount: Int = 1
)
