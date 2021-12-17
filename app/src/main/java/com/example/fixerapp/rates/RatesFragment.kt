package com.example.fixerapp.rates

import android.os.Build
import android.os.Bundle
import android.provider.Telephony
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fixerapp.databinding.FragmentRatesBinding
import com.example.fixerapp.network.RecyclerItem

class RatesFragment : Fragment() {

    private val viewModel: RatesViewModel by lazy {
        ViewModelProvider(this).get(RatesViewModel::class.java)
    }

    private var _binding: FragmentRatesBinding? = null
    private val binding get() = _binding!!


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentRatesBinding.inflate(inflater, container, false)
        binding.ratesViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = RatesAdapter(RatesAdapter.OnClickListener{
            viewModel.displayDetails(it)
        })
        val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
        binding.recyclerView.addOnScrollListener(InfiniteScrollListener({ viewModel.updateDate() }, layoutManager))

        viewModel.navToDetail.observe(viewLifecycleOwner, Observer {
            if(null != it){
                this.findNavController().navigate(
                    RatesFragmentDirections.actionRatesFragmentToDetailsFragment(it))
                viewModel.displayDetailsComplete()
            }
        })
        return binding.root
    }
}


