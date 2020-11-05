package dz.islem.weatherapp.data.remote.model.weather

import kotlinx.serialization.Serializable

@Serializable
data class Forecast(
    val forecastday: List<Forecastday>
)