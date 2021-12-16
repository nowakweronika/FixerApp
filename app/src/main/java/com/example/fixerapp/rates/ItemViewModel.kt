package com.example.fixerapp.rates

import androidx.annotation.LayoutRes
import com.example.fixerapp.R

interface ItemViewModel {
    @get:LayoutRes
    val layoutId: Int
    val viewType: Int get() = 0
}
class HeaderViewModel(val currentDate: String) : ItemViewModel{
    override val layoutId: Int = R.layout.header_item
    override val viewType: Int = RatesAdapter.DATE_VALUE
}
class CurrencyViewModel(val currency: String, val rate: String) : ItemViewModel{
    override val layoutId: Int = R.layout.rate_item
    override val viewType: Int = RatesAdapter.RATE_VALUE
}