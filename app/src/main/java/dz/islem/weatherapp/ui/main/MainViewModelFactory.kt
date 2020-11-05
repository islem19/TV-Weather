package dz.islem.weatherapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dz.islem.weatherapp.data.remote.RetrofitClient
import dz.islem.weatherapp.data.remote.api.WeatherApi

class MainViewModelFactory(val retrofitClient : RetrofitClient) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(retrofitClient) as T
        throw IllegalArgumentException("Unknown VM")
    }

}