package dz.islem.weatherapp.data.remote.api

import dz.islem.weatherapp.data.remote.model.weather.WeatherForecast
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET("forecast.json")
    fun getWeatherForecast(@Query("key") key : String ,
                           @Query("q") location : String,
                           @Query("days") days : Int ): Observable<WeatherForecast>
}