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
