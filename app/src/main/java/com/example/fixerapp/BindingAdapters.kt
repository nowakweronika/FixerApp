package com.example.fixerapp

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fixerapp.network.RecyclerItem
import com.example.fixerapp.rates.RatesAdapter

@BindingAdapter("recyclerValues")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<RecyclerItem>?){
    val adapter = recyclerView.adapter as RatesAdapter//getOrCreateAdapter(recyclerView)
    adapter.updateItems(data)
}
/*
fun bindItemViewModels(recyclerView: RecyclerView, itemViewModels: List<ItemViewModel>?) {
    val adapter = getOrCreateAdapter(recyclerView)
    adapter.updateItems(itemViewModels)
}*/

/*
private fun getOrCreateAdapter(recyclerView: RecyclerView): RatesAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is RatesAdapter) {
        recyclerView.adapter as RatesAdapter
    } else {
        val bindableRecyclerAdapter = RatesAdapter()
        recyclerView.adapter = bindableRecyclerAdapter
        bindableRecyclerAdapter
    }
}*/
