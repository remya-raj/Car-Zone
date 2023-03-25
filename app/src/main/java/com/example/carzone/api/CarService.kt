package com.example.carzone.api

import com.example.carzone.models.CarsData
import retrofit2.Response
import retrofit2.http.GET

interface CarService {
    @GET("/v1/a53f9e9d-edf0-496c-a705-33040569a7da")
    suspend fun getCarData() : Response<CarsData>
}