package dz.islem.weatherapp.data.remote.model.location

import kotlinx.serialization.Serializable

@Serializable
data class Location (
    val `as`: String,
    val city: String,
    val country: String,
    val countryCode: String,
    val isp: String,
    val lat: Double,
    val lon: Double,
    val org: String,
    val query: String,
    val region: String,
    val regionName: String,
    val status: String,
    val timezone: String,
    val zip: String
)