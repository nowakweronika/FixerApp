package com.example.fixerapp.network

data class FixerData(
    val date: String,
    val rates: Map<String, Double>,
    val success: Boolean,
)