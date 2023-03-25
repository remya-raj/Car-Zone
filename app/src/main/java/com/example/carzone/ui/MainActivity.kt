package com.example.carzone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.carzone.adapters.CarServicesAdapter
import com.example.carzone.adapters.MainAdapter
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
        initData()
        initView()
    }

    private fun initView() {
        mainViewModel.carsData.observe(this, Observer {

            binding.viewBgProgressBar.visibility = View.GONE
            binding.progressBar.visibility = View.GONE

            it?.let {
                if (it.status == "success") {

                    val offerLayoutAdapter = LinearLayoutManager(this)
                    val offersAdapter = MainAdapter(this, it.feeds)
                    binding.rvMain.layoutManager = offerLayoutAdapter
                    binding.rvMain.adapter = offersAdapter

                } else {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun initData() {
        val carService = RetrofitHelper().getInstance().create(CarService::class.java)
        val carRepository = CarRepository(carService)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(carRepository)).get(MainViewModel::class.java)
    }
}