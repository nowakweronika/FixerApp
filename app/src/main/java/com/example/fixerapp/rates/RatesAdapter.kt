package com.example.fixerapp.rates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fixerapp.R
import java.time.LocalDate

class RatesAdapter : RecyclerView.Adapter<RatesAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private fun bindDate(item: LocalDate){
            itemView.findViewById<TextView>(R.id.dateTextView)?.text = item.toString()
        }
    }
    companion object{
        private const val DATE_VALUE = 0
        private const val RATE_VALUE = 1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val layout = when (viewType) {
            DATE_VALUE -> R.layout.header_item
            RATE_VALUE -> R.layout.rate_item
            else -> throw IllegalArgumentException("Invalid view type")
        }
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}