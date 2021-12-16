package com.example.fixerapp.rates

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fixerapp.network.FixerApi
import com.example.fixerapp.network.FixerData
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class RatesViewModel: ViewModel() {

    private val _date = MutableLiveData<LocalDate>()
    val date: LiveData<LocalDate> get() = _date

    private val _dailyRates = MutableLiveData<FixerData>()
    val dailyRates: LiveData<FixerData> get() = _dailyRates

    private val _rates = MutableLiveData<Map<String, Double>>()
    val rates: LiveData<Map<String, Double>> get() = _rates

    private val _data = MutableLiveData<List<ItemViewModel>>()
    val data: LiveData<List<ItemViewModel>> get() = _data

    private val viewData = mutableListOf<ItemViewModel>()


    init {
        setCurrentDate()
        getFixerData(_date.value.toString())
    }

    private fun setCurrentDate(){
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = LocalDateTime.now().format(formatter)
        _date.value = LocalDate.parse(formatted, formatter)
    }

    fun updateDate(){
        _date.value = _date.value!!.minusDays(1)
        getFixerData(_date.value.toString())
    }

    private fun getFixerData(myDate: String) {
        viewData.add(HeaderViewModel(_date.value.toString()))
        viewModelScope.launch {
            try {
                _dailyRates.value = FixerApi.retrofitService.getData(myDate)
                _rates.value = _dailyRates.value!!.rates
                for(item in _rates.value!!.entries){
                    viewData.add(CurrencyViewModel(item.key, item.value.toString()))
                    Log.i("DATA", item.toString())
                }
                _data.value = viewData
            } catch (e: Exception) {
                Log.i("API_SERVICE", e.toString())
            }
        }
    }

}