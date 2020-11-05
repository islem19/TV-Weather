package dz.islem.weatherapp.data.remote.model.weather
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecast(
    val alert: Alert,
    val current: Current,
    val forecast: Forecast,
    val location: Location
)