package com.example.hastakalaassist

import androidx.compose.runtime.mutableStateListOf

data class Sale(
    val product: String,
    val color: String,
    val quantity: Int
)

object AppData {
    val sales = mutableStateListOf<Sale>()
}