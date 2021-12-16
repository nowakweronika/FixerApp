package com.example.fixerapp

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fixerapp.rates.ItemViewModel
import com.example.fixerapp.rates.RatesAdapter

@BindingAdapter("itemViewModels")
fun bindItemViewModels(recyclerView: RecyclerView, itemViewModels: List<ItemViewModel>?) {
    val adapter = getOrCreateAdapter(recyclerView)
    adapter.updateItems(itemViewModels)
}

private fun getOrCreateAdapter(recyclerView: RecyclerView): RatesAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is RatesAdapter) {
        recyclerView.adapter as RatesAdapter
    } else {
        val bindableRecyclerAdapter = RatesAdapter()
        recyclerView.adapter = bindableRecyclerAdapter
        bindableRecyclerAdapter
    }
}
