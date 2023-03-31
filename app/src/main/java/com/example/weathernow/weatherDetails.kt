package com.example.weathernow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.weather_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val Base_Url="http://api.openweathermap.org/"
class weatherDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_details)

        val latitude=intent.getStringExtra("Latitude")
        val longitude=intent.getStringExtra("Longitude")

        txtLatitudeData.text=latitude
        txtLongitudeData.text=longitude
        //getAirQualityData()
    }


    private fun getAirQualityData() {
        val retrofitBuilder= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Base_Url)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData=retrofitBuilder.getdata(2.5,"air_pollution","-87.57843","43.29352","db65b20b5759421e1a0f7e27cb3dcfc9")

        retrofitData.enqueue(object : Callback<List<Components>?> {
            override fun onResponse(
                call: Call<List<Components>?>,
                response: Response<List<Components>?>
            ) {
                val responseBody=response.body()!!

                val myStringBuilder=StringBuilder()
                for(myData in responseBody){
                    myStringBuilder.append(myData.co)
                }
                txtPressure.text=myStringBuilder
            }

            override fun onFailure(call: Call<List<Components>?>, t: Throwable) {
                Log.d("AirQualityDataOnFailure","onFailure "+t.message)
            }
        })
    }
}