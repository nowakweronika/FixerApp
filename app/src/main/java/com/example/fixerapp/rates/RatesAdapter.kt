package com.example.fixerapp.rates

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fixerapp.databinding.HeaderItemBinding
import com.example.fixerapp.databinding.RateItemBinding
import com.example.fixerapp.network.RecyclerItem

class RatesAdapter(
    private val onClickListener: OnClickListener
        ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var recyclerValues: List<RecyclerItem> = listOf()

    companion object{
        const val DATE_VALUE = 0
        const val RATE_VALUE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == DATE_VALUE) {
            return DateViewHolder(
                HeaderItemBinding.inflate(LayoutInflater.from(parent.context))
            )
        } else{
            return RateViewHolder(
                RateItemBinding.inflate(LayoutInflater.from(parent.context))
            )
        }
    }

    fun updateItems(items: List<RecyclerItem>?) {
        recyclerValues = items ?: emptyList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (recyclerValues[position].viewType === DATE_VALUE) {
            val item = recyclerValues[position]
            val dateHolder = holder as DateViewHolder
            //dateHolder.date.text = item.date
            dateHolder.bind(item)
            Log.i("ON_BIND_DATE", item.toString())
        } else {
            val item = recyclerValues[position]
            val rateHolder = holder as RateViewHolder
            /*rateHolder.currency.text = item.currency
            rateHolder.rate.text = item.rate.toString()*/
            rateHolder.itemView.setOnClickListener {
                onClickListener.onClick(item)
            }
            rateHolder.bind(item)
            Log.i("ON_BIND_RATE", item.toString())
            //TODO setOnClickListener
        }
    }

    override fun getItemCount(): Int = recyclerValues.size
    override fun getItemViewType(position: Int): Int {
        return recyclerValues[position].viewType
    }

    /*private inner class DateViewHolder(binding: HeaderItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val date: TextView = binding.dateTextView
    }*/
    /*private inner class RateViewHolder(binding: RateItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        val currency: TextView = binding.currencyTextView
        val rate: TextView = binding.rateTextView
        val itemContainer: View = binding.root
    }*/
    class DateViewHolder(private var binding: HeaderItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecyclerItem) {
            binding.itemViewModel = item
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }
    class RateViewHolder(private var binding: RateItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecyclerItem) {
            binding.itemViewModel = item
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }
    class OnClickListener(val clickListener: (item: RecyclerItem) -> Unit){
        fun onClick(item: RecyclerItem) = clickListener(item)
    }
}
class InfiniteScrollListener(
    val func: () -> Unit,
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private var loading = true
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            visibleItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }
            if (!loading && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
                // End has been reached
                func()
                loading = true
            }
        }
    }
}