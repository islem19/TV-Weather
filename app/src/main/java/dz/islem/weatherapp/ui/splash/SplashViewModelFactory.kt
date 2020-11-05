package dz.islem.weatherapp.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dz.islem.weatherapp.data.remote.api.LocationApi
import dz.islem.weatherapp.data.remote.api.WeatherApi

class SplashViewModelFactory(private val locationApi: LocationApi,private val weatherApi: WeatherApi) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SplashViewModel::class.java)){
            return SplashViewModel(locationApi,weatherApi) as T
        }
        throw IllegalArgumentException("Unknown Class")
    }

}