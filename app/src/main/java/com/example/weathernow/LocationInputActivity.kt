package com.example.weathernow

import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_location_input.*
import kotlinx.android.synthetic.main.weather_details.*
import java.security.Permission

class LocationInputActivity : AppCompatActivity() {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    //permissionId is just an int that must be unique for a particular permission
    private var permissionId=1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_input)

        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this)

        txtCurrentLocation.setOnClickListener{
            fetchLocation()

        }

    }

    private fun fetchLocation() {
        val task=fusedLocationProviderClient.lastLocation
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)
            !=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
            !=PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1)
            return
        }
        task.addOnSuccessListener {
            if(it!=null){
                val latitudeValue=it.latitude
                val longitudeValue=it.longitude
                val intent=Intent(this,weatherDetails::class.java).also {
                    it.putExtra("Latitude",latitudeValue.toString())
                    it.putExtra("Longitude",longitudeValue.toString())
                    startActivity(it)
                }

                //Toast.makeText(applicationContext,"${it.latitude}  ${it.longitude}",Toast.LENGTH_SHORT).show()
            }
        }

    }

//    //Here we are creating a fun that will give us the last location
//    private fun getLastLocation(){
//        //first we check permission
//        if(checkPermission()){
//            //here we check if location services is enabled
//            if(isLocationEnabled()){
//                //Now lets get the location
//                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task->
//                    var location=task.result
//                    if(location==null){
//                        //if the location is null we will get new user location
//                        // so we need to create a new function
//
//                    }else{
//                        txtLocation.text="Your current coordinates are:\nLat:"+location.latitude+" ; Long: "+location.longitude
//                    }
//                }
//            }else{
//                Toast.makeText(this,"Please enable the location services",Toast.LENGTH_SHORT).show()
//            }
//        }else{
//            requestPermission()
//        }
//    }
//
//
//    //Here we create a function which checks the uses permission
//    private fun checkPermission():Boolean{
//        if(
//            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED
//            ||ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED
//        ){
//            return true
//        }
//        return false
//    }
//
//    //here we create a fun which allows us to get user permission
//    private fun requestPermission(){
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION),
//            permissionId
//        )
//    }
//
//    //Now here we need a function that checks if the location services of the device is enabled or not
//    private fun isLocationEnabled():Boolean{
//
//        var locationManager=getSystemService(LOCATION_SERVICE) as LocationManager
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)|| locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
//    }
//
//    //below is an inbuilt fun that checks the permission result
//    //here we have used it to take help in debugging
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode==permissionId){
//            if (grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                Log.d("Debug","You hav the permission")
//            }
//        }
//    }
}