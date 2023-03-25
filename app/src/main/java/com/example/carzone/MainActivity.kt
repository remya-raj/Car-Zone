package com.example.carzone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.carzone.adapters.CarServicesAdapter
import com.example.carzone.adapters.OffersAdapter
import com.example.carzone.api.CarService
import com.example.carzone.api.RetrofitHelper
import com.example.carzone.databinding.ActivityMainBinding
import com.example.carzone.repository.CarRepository
import com.example.carzone.viewmodels.MainViewModel
import com.example.carzone.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val carService = RetrofitHelper().getInstance().create(CarService::class.java)
        val carRepository = CarRepository(carService)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(carRepository)).get(MainViewModel::class.java)

        mainViewModel.carsData.observe(this, Observer {
            it?.let {
                binding.tvName.text = it.feeds[0].car_name
                binding.tvNumber.text = it.feeds[0].car_reg_no
                binding.tvType.text = it.feeds[0].car_type + " - " + it.feeds[0].fuel_type
                Glide
                    .with(this)
                    .load(it.feeds[0].image_url)
                    .centerCrop()
                    .into(binding.ivCar)

                val carServiceLayoutManager = GridLayoutManager(this, 4)
                val carServiceAdapter = CarServicesAdapter(it.feeds[1].services)
                binding.rvCarServices.layoutManager = carServiceLayoutManager
                binding.rvCarServices.adapter = carServiceAdapter

                val offerLayoutAdapter = LinearLayoutManager(this)
                val offersAdapter = OffersAdapter(it.feeds[2].banners)

                binding.rvOffers.layoutManager = offerLayoutAdapter
                binding.rvOffers.adapter = offersAdapter
            }
        })
    }
}