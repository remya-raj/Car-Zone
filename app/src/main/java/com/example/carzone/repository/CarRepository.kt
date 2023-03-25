package com.example.carzone.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.carzone.api.CarService
import com.example.carzone.models.CarsData

class CarRepository(private val carService: CarService) {

    private val carMutableLiveData = MutableLiveData<CarsData>()
    private val TAG = "CarRepository"

    val carData: LiveData<CarsData>
    get() = carMutableLiveData

    suspend fun getCarData() {
        try {
            val result = carService.getCarData()
            if (result.body() != null) {
                carMutableLiveData.postValue(result.body())
            }
        } catch (e: java.lang.Exception) {
            Log.d(TAG, "Something went wrong")
        }

    }
}