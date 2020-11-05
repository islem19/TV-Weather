package dz.islem.weatherapp.ui.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dz.islem.weatherapp.data.remote.RetrofitClient

class ForecastViewModelFactory(val retrofitClient: RetrofitClient) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForecastViewModel::class.java))
            return ForecastViewModel(retrofitClient) as T
        throw IllegalArgumentException("Unknown VM")
    }

}