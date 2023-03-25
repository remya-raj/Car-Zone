package com.example.carzone.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carzone.models.CarsData
import com.example.carzone.repository.CarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val carRepository: CarRepository): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            carRepository.getCarData()
        }
    }

    val carsData: LiveData<CarsData>
    get() = carRepository.carData
}