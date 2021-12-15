package com.example.fixerapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fixerapp.network.FixerApi
import com.example.fixerapp.network.FixerData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getApiData()
    }

    private fun getApiData() {
        FixerApi.retrofitService.getData("2013-03-16").enqueue(
            object: Callback<FixerData> {
                override fun onResponse(call: Call<FixerData>, response: Response<FixerData>) {
                    Log.i("API_SERVICE", "udało się")
                    val txt: TextView = findViewById(R.id.txt)
                    txt.text = response.body()?.date
                    Log.i("RESPONSE", response.body().toString())
                    Log.d("API_SERVICE", call.request().url().toString())
                }

                override fun onFailure(call: Call<FixerData>, t: Throwable) {
                    Log.i("API_SERVICE", t.toString())
                    Log.d("API_SERVICE", call.request().url().toString())
                }
            }
        )
    }
}