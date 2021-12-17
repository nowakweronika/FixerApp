package com.example.fixerapp.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class FixerData(
    val date: String,
    val rates: Map<String, Double>,
    val success: Boolean,
)
@Parcelize
data class RecyclerItem(
    val viewType: Int,
    val currency: String,
    val rate: String,
    val date: String?,
) : Parcelable