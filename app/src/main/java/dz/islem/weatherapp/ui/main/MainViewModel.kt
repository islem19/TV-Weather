package dz.islem.weatherapp.ui.main

import androidx.lifecycle.MutableLiveData
import dz.islem.weatherapp.data.remote.RetrofitClient
import dz.islem.weatherapp.data.remote.model.weather.WeatherForecast
import dz.islem.weatherapp.ui.base.BaseViewModel

class MainViewModel(val retrofitClient: RetrofitClient) : BaseViewModel() {
    private var weatherData = MutableLiveData<WeatherForecast>()

    fun getWeatherData(): MutableLiveData<WeatherForecast> {
        return weatherData
    }

    fun loadWeatherForecast(){
        weatherData.postValue(RetrofitClient.getWeatherForecast())
    }

}