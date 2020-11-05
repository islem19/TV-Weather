package dz.islem.weatherapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dz.islem.weatherapp.BuildConfig
import dz.islem.weatherapp.R
import dz.islem.weatherapp.data.remote.RetrofitClient
import dz.islem.weatherapp.data.local.LocationSharedPrefs
import dz.islem.weatherapp.ui.base.BaseActivity
import dz.islem.weatherapp.ui.main.MainActivity


class SplashActivity  : BaseActivity<SplashViewModel>() {
    override fun createViewModel(): SplashViewModel {
        val splashViewModelFactory = SplashViewModelFactory(
            RetrofitClient.getLocationApi(),
            RetrofitClient.getWeatherApi()
        )
        return ViewModelProvider(this, splashViewModelFactory).get(SplashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.getLocationData().observe(this, Observer {
            LocationSharedPrefs.setDefaultCity(it.city)
            loadWeather(it.city)
        })

        if (LocationSharedPrefs.getDefaultCity() == null) {
            viewModel.loadLocation()
        } else {
            LocationSharedPrefs.getDefaultCity()?.let { loadWeather(it) }
        }


        viewModel.getWeatherData().observe(this,
            Observer {
                RetrofitClient.setWeatherForecast(it)
                startMainActivity()
            })
    }

    private fun loadWeather(location: String) {
        viewModel.loadWeatherForecast(BuildConfig.WEATHER_API_KEY, location)
    }

    private fun startMainActivity() {
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 500)
    }
}