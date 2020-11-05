package dz.islem.weatherapp.ui.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import dz.islem.weatherapp.R
import dz.islem.weatherapp.data.remote.RetrofitClient
import dz.islem.weatherapp.data.remote.model.weather.WeatherForecast
import dz.islem.weatherapp.ui.base.BaseActivity
import dz.islem.weatherapp.util.StringUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.current_weather_layout.*
import kotlinx.android.synthetic.main.item_weather_layout.*


class MainActivity : BaseActivity<MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadWeatherForecast()
        setContentView(R.layout.activity_main)
        viewModel.getWeatherData().observe(this,
            Observer {
                updateUI(it)
            })
    }

    private fun updateUI(weatherForecast: WeatherForecast?) {
        // general info
        tvLocation.text = StringUtils.formatLocation(
            weatherForecast?.location?.name,
            weatherForecast?.location?.country
        );
        tvLocalTime.text = weatherForecast?.location?.localtime
        // current condition info
        tvCondition.text = weatherForecast?.current?.condition?.text
        tvTemperature.text = StringUtils.formatTemperature(weatherForecast?.current?.temp_c.toString())
        Glide.with(this)
            .load(StringUtils.formatUrl(weatherForecast?.current?.condition?.icon))// image url
            .error(R.drawable.condition_error) // any image in case of error
            .override(120, 120) // resizing
            .centerCrop()
            .into(ivIcon) // imageview object
        // details info
        tvItemWind.text = StringUtils.formatWind(weatherForecast?.current?.wind_kph.toString())
        tvItemWindDir.text = weatherForecast?.current?.wind_dir
        tvItemPressure.text = weatherForecast?.current?.pressure_mb.toString()
        tvItemFeelsLike.text = StringUtils.formatTemperature(weatherForecast?.current?.feelslike_c.toString())
        tvItemPrecip.text = StringUtils.formatPrecip(weatherForecast?.current?.precip_mm.toString())
        tvItemHumidity.text = StringUtils.formatPercentage(weatherForecast?.current?.humidity.toString())
        tvItemCloud.text = StringUtils.formatPercentage(weatherForecast?.current?.cloud.toString())
        tvItemUv.text = weatherForecast?.current?.uv.toString()
        tvLastUpdate.text = resources.getString(
            R.string.lastUpdate,
            weatherForecast?.current?.last_updated
        )

    }

    override fun createViewModel(): MainViewModel {
        val mainViewModelFactory = MainViewModelFactory(RetrofitClient)
        return ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT ) {
            ivLeftArrow.startAnimation(AnimationUtils.loadAnimation(this, R.anim.animation))
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT ) {
            ivRightArrow.startAnimation(AnimationUtils.loadAnimation(this, R.anim.animation))
        }
        return super.onKeyDown(keyCode, event)
    }
}