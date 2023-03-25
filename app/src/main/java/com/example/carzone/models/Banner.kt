package com.example.carzone.models

data class Banner(
    val car_required: Boolean,
    val image: String,
    val image_height: Int,
    val is_custom_banner: Boolean,
    val name: String,
    val provider_name: String,
    val redirect_url: String,
    val screen_name: String
)