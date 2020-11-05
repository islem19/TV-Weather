package dz.islem.weatherapp.data.remote.model.weather

import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    val code: Int,
    val icon: String,
    val text: String
)