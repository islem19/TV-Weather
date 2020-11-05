package dz.islem.weatherapp.ui.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import dz.islem.weatherapp.data.remote.api.LocationApi
import dz.islem.weatherapp.data.remote.api.WeatherApi
import dz.islem.weatherapp.data.remote.model.location.Location
import dz.islem.weatherapp.data.remote.model.weather.WeatherForecast
import dz.islem.weatherapp.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashViewModel(private val locationApi: LocationApi, private val weatherApi: WeatherApi) : BaseViewModel(){
    private var locationData = MutableLiveData<Location>()
    private var weatherData = MutableLiveData<WeatherForecast>()


    fun getLocationData(): MutableLiveData<Location> {
        return locationData
    }

    fun loadLocation() {
        compositeDisposable.add(
            locationApi.getLocation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    locationData.postValue(it)
                }, {
                    Log.e("TAG", "loadLocation: $it" )
                }
                )
        )
    }


    fun getWeatherData(): MutableLiveData<WeatherForecast> {
        return weatherData
    }

    fun loadWeatherForecast(key: String,location: String, days: Int = 10){
        compositeDisposable.add(
            weatherApi.getWeatherForecast(key,location,days)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    weatherData.postValue(it)
                }, {
                    Log.e("TAG","weatherData $it")
                }
                )
        )
    }


}