package com.example.listadecompras

import android.graphics.Bitmap

data class Product(
    val name : String,
    val quantity: Int,
    val price: Double,
    var photo: Bitmap? = null
)