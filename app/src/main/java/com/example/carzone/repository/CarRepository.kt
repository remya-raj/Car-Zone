package com.example.carzone.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.carzone.api.CarService
import com.example.carzone.models.CarsData

class CarRepository(private val carService: CarService) {

    private val carMutableLiveData = MutableLiveData<CarsData>()

    val carData: LiveData<CarsData>
    get() = carMutableLiveData

    suspend fun getCarData() {
        val result = carService.getCarData()
        if (result.body() != null) {
            carMutableLiveData.postValue(result.body())
        }
    }
}