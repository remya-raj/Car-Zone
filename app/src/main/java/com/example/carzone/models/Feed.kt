package com.example.carzone.models

data class Feed(
    val banners: List<Banner>,
    val car_name: String,
    val car_reg_no: String,
    val car_type: String,
    val component_position: Int,
    val fuel_type: String,
    val heading: String,
    val image_url: String,
    val screen: String,
    val services: List<Service>,
    val show_all_services: Boolean
)