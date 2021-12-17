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
import com.example.fixerapp.network.RecyclerItem
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

    private val _viewData = mutableListOf<RecyclerItem>()

    private val _correctData = MutableLiveData<List<RecyclerItem>>()
    val correctData: LiveData<List<RecyclerItem>> get() = _correctData

    private val _navToDetail = MutableLiveData<RecyclerItem>()
    val navToDetail: LiveData<RecyclerItem> get() = _navToDetail

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
        _viewData.add(RecyclerItem(RatesAdapter.DATE_VALUE,"", "",_date.value.toString()))
        viewModelScope.launch {
            try {
                _dailyRates.value = FixerApi.retrofitService.getData(myDate)
                _rates.value = _dailyRates.value!!.rates
                for(item in _rates.value!!.entries){
                    _viewData.add(
                        RecyclerItem(RatesAdapter.RATE_VALUE,
                        item.key, item.value.toString(), _date.value.toString()))
                }
                _correctData.value = _viewData
            } catch (e: Exception) {
                Log.i("API_SERVICE", e.toString())
            }
        }
    }
    fun displayDetails(item: RecyclerItem){
        _navToDetail.value = item
    }
    /*Need this to mark nav state to complete and to avoid the nav being triggered
    * again when the user returns from the detail*/
    fun displayDetailsComplete(){
        _navToDetail.value = null
    }
}