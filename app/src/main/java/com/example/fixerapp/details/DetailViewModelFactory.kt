package com.example.fixerapp.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fixerapp.network.RecyclerItem
import java.lang.IllegalArgumentException

class DetailViewModelFactory(
    private val recyclerItem: RecyclerItem,
    private val application: Application)
    : ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailsViewModel::class.java)){
            return DetailsViewModel(recyclerItem, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class.")
    }

}