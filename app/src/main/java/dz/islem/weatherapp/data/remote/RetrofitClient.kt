package dz.islem.weatherapp.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dz.islem.weatherapp.BuildConfig
import dz.islem.weatherapp.data.remote.api.LocationApi
import dz.islem.weatherapp.data.remote.api.WeatherApi
import dz.islem.weatherapp.data.remote.model.weather.WeatherForecast
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object RetrofitClient {

    private lateinit var weatherForecast: WeatherForecast
    private val locationInstance by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.LOCATION_API_URL)
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private val weatherInstance by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.WEATHER_API_URL)
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getWeatherApi() : WeatherApi {
        return weatherInstance.create(WeatherApi::class.java)
    }

    fun getLocationApi() : LocationApi {
        return locationInstance.create(LocationApi::class.java)
    }



    fun setWeatherForecast(weatherForecast: WeatherForecast) {
            this.weatherForecast = weatherForecast
    }

    fun getWeatherForecast(): WeatherForecast {
        return weatherForecast
    }
}