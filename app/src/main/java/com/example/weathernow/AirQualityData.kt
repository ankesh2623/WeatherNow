package com.example.weathernow

data class AirQualityData(
    val coord: Coord,
    val list: List<Components>
)