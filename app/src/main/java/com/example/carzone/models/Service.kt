package com.example.carzone.models

data class Service(
    val car_required: Boolean,
    val image_url: String,
    val name: String,
    val provider_name: String,
    val redirect_url: String,
    val screen_name: String
)