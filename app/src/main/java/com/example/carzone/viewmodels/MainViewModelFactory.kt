package com.example.carzone.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.carzone.repository.CarRepository

class MainViewModelFactory(private val carRepository: CarRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(carRepository) as T
    }

}