package com.example.fixerapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fixerapp.network.FixerApi
import com.example.fixerapp.network.FixerData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val txt: TextView = findViewById(R.id.txt)
        val button: Button = findViewById(R.id.button)

        lateinit var parsedDate: LocalDate
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formatted = LocalDateTime.now().format(formatter)
            parsedDate = LocalDate.parse(formatted, formatter)
        } else {
            var date = Date()
            val formatter = SimpleDateFormat("yyyy-mm-dd")
            val answer: String = formatter.format(date)
        }
        button.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                parsedDate = parsedDate.minusDays(1)
            }
            txt.text = parsedDate.toString()
            getApiData(parsedDate.toString())
        }*/
    }

    /*private fun getApiData(date: String) {
        FixerApi.retrofitService.getData(date).enqueue(
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
    }*/
}