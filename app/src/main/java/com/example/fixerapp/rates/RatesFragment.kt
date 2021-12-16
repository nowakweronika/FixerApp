package com.example.fixerapp.rates

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fixerapp.databinding.FragmentRatesBinding

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
        return binding.root
    }
}

