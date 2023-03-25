package com.example.carzone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carzone.api.CarService
import com.example.carzone.api.RetrofitHelper
import com.example.carzone.repository.CarRepository
import com.example.carzone.viewmodels.MainViewModel
import com.example.carzone.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val carService = RetrofitHelper().getInstance().create(CarService::class.java)
        val carRepository = CarRepository(carService)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(carRepository)).get(MainViewModel::class.java)

        mainViewModel.carsData.observe(this, Observer {
            findViewById<TextView>(R.id.tvResponse).text = it.feeds.toString()
        })
    }
}