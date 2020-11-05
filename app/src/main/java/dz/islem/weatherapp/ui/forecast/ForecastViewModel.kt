package dz.islem.weatherapp.ui.forecast

import androidx.lifecycle.MutableLiveData
import dz.islem.weatherapp.data.remote.RetrofitClient
import dz.islem.weatherapp.data.remote.model.weather.WeatherForecast
import dz.islem.weatherapp.ui.base.BaseViewModel

class ForecastViewModel(private val retrofitClient: RetrofitClient) : BaseViewModel() {

    private var weatherData = MutableLiveData<WeatherForecast>()

    fun getWeatherData(): MutableLiveData<WeatherForecast> {
        return weatherData
    }

    fun loadWeatherForecast(){
        weatherData.postValue(retrofitClient.getWeatherForecast())

    }

}
