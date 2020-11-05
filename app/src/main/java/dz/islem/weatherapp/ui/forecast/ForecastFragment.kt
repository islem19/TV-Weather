package dz.islem.weatherapp.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import dz.islem.weatherapp.R
import dz.islem.weatherapp.data.remote.RetrofitClient
import dz.islem.weatherapp.data.remote.model.weather.Forecastday
import dz.islem.weatherapp.ui.base.BaseFragment


class ForecastFragment : BaseFragment<ForecastViewModel>() {
    private lateinit var mAdapter: ForecastPagerAdapter
    private var weatherForecastList : List<Forecastday> = ArrayList<Forecastday>()

    override fun createViewModel(): ForecastViewModel {
        val forecastViewModelFactory = ForecastViewModelFactory(RetrofitClient)
        return ViewModelProvider(this, forecastViewModelFactory).get(ForecastViewModel::class.java)
    }

    override fun onCreate(paramBundle: Bundle?) {
        super.onCreate(paramBundle)
        mAdapter = ForecastPagerAdapter(weatherForecastList)

        viewModel.getWeatherData().observe(this,
            Observer {
                updatePager(it.forecast.forecastday)
            })
        viewModel.loadWeatherForecast()
    }

    override fun onCreateView(
        paramLayoutInflater: LayoutInflater,
        paramViewGroup: ViewGroup?,
        paramBundle: Bundle?
    ): View? {
        val view: View = paramLayoutInflater.inflate(
            R.layout.forecast_layout,
            paramViewGroup,
            false
        )
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vpForecast = view.findViewById<ViewPager>(R.id.vpForecast)
        vpForecast.adapter = mAdapter
        vpForecast.offscreenPageLimit = 3
        vpForecast.requestFocus()
    }


    private fun updatePager(forecastday: List<Forecastday>) {
        mAdapter.updateData(forecastday)
        mAdapter.notifyDataSetChanged()
    }

}