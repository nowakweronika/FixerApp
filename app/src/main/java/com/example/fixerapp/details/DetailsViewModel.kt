package com.example.fixerapp.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fixerapp.network.RecyclerItem

class DetailsViewModel(rate: RecyclerItem, app: Application)
    :AndroidViewModel(app) {

    private val _rateInfo = MutableLiveData<RecyclerItem>()
    val rateInfo: LiveData<RecyclerItem> get() = _rateInfo

    init {
        _rateInfo.value = rate
    }

}