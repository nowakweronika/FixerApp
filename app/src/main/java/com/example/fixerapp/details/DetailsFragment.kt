package com.example.fixerapp.details

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.fixerapp.R
import com.example.fixerapp.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val recyclerItem = DetailsFragmentArgs.fromBundle(requireArguments()).selectedRate
        val viewModelFactory = DetailViewModelFactory(recyclerItem, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(DetailsViewModel::class.java)
        return binding.root
    }

}