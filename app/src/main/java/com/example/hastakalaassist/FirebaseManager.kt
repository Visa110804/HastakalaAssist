package com.example.hastakalaassist

import com.google.firebase.firestore.FirebaseFirestore

object FirebaseManager {

    private val db = FirebaseFirestore.getInstance()

    fun saveSale(sale: Sale) {

        val saleData = hashMapOf(
            "product" to sale.product,
            "color" to sale.color,
            "quantity" to sale.quantity
        )

        db.collection("sales")
            .add(saleData)
    }
}