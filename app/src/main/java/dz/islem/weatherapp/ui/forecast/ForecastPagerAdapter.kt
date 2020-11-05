package dz.islem.weatherapp.ui.forecast

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import dz.islem.weatherapp.R
import dz.islem.weatherapp.data.remote.model.weather.Forecastday
import dz.islem.weatherapp.util.StringUtils


class ForecastPagerAdapter(private var weatherForecastList: List<Forecastday>) : PagerAdapter(){

    override fun getCount(): Int {
        return weatherForecastList.size?: 0
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    fun updateData(forecastday: List<Forecastday>) {
        this.weatherForecastList = forecastday
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    private class ViewHolder(view: View) {
        val tvDayDate = view.findViewById<TextView>(R.id.tvDayDate)
        val tvMaxtemp = view.findViewById<TextView>(R.id.tvItemMaxTemp)
        val tvMinTemp = view.findViewById<TextView>(R.id.tvItemMinTemp)
        val tvMaxWind = view.findViewById<TextView>(R.id.tvItemMaxWind)
        val tvTotalPrecip = view.findViewById<TextView>(R.id.tvItemTotalPrecip)
        val tvChanceRain = view.findViewById<TextView>(R.id.tvItemChanceRain)
        val tvChanceSnow = view.findViewById<TextView>(R.id.tvItemChanceSnow)

        val tvSunrise = view.findViewById<TextView>(R.id.tvItemSunrise)
        val tvSunset = view.findViewById<TextView>(R.id.tvItemSunset)
        val tvMoonrise = view.findViewById<TextView>(R.id.tvItemMoonrise)
        val tvMoonset = view.findViewById<TextView>(R.id.tvItemMoonset)
        val tvMoonphase = view.findViewById<TextView>(R.id.tvItemMoonphase)
        val tvMoonIllumination = view.findViewById<TextView>(R.id.tvItemMoonIllumination)
        val rvHourForecast = view.findViewById<RecyclerView>(R.id.rvHourForecast)

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(
            R.layout.forecastday_layout,
            container,
            false
        )
        val viewHolder = ViewHolder(view)
        val forecastday = weatherForecastList[position]

        viewHolder.tvDayDate.text = forecastday.date
        updateDayForecastUi(viewHolder, forecastday)
        updateAstroUi(viewHolder, forecastday)
        updateRecyclerViewUi(container.context,viewHolder,forecastday)

        container.addView(view)
        return view
    }

    private fun updateRecyclerViewUi(
        context : Context,
        viewHolder: ViewHolder,
        forecastday: Forecastday
    ) {
        viewHolder.rvHourForecast.adapter = RecyclerViewAdapter(forecastday.hour)
        val layoutManager: LinearLayoutManager = object : LinearLayoutManager(context) {
            override fun smoothScrollToPosition(
                recyclerView: RecyclerView,
                state: RecyclerView.State,
                position: Int
            ) {
                val smoothScroller: LinearSmoothScroller = object : LinearSmoothScroller(context) {
                    private val SPEED = 2500f // Change this value (default=25f)
                    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                        return SPEED / displayMetrics.densityDpi
                    }
                }
                smoothScroller.targetPosition = position
                startSmoothScroll(smoothScroller)
            }
        }

        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        viewHolder.rvHourForecast.layoutManager = layoutManager
        viewHolder.rvHourForecast.smoothScrollToPosition(forecastday.hour.size);

        viewHolder.rvHourForecast.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollHorizontally(1)) {
                    recyclerView.smoothScrollToPosition(0);
                }
                if (!recyclerView.canScrollHorizontally(-1)) {
                    recyclerView.smoothScrollToPosition(forecastday.hour.size);
                }
            }
        })
    }

    private fun updateAstroUi(
        viewHolder: ViewHolder,
        forecastday: Forecastday
    ) {
        viewHolder.tvSunrise.text = forecastday.astro.sunrise
        viewHolder.tvSunset.text = forecastday.astro.sunset
        viewHolder.tvMoonrise.text = forecastday.astro.moonrise
        viewHolder.tvMoonset.text = forecastday.astro.moonset
        viewHolder.tvMoonphase.text = forecastday.astro.moon_phase
        viewHolder.tvMoonIllumination.text = forecastday.astro.moon_illumination
    }

    private fun updateDayForecastUi(
        viewHolder: ViewHolder,
        forecastday: Forecastday
    ) {
        viewHolder.tvMaxtemp.text = StringUtils.formatTemperature(forecastday.day.maxtemp_c.toString())
        viewHolder.tvMinTemp.text = StringUtils.formatTemperature(forecastday.day.mintemp_c.toString())
        viewHolder.tvMaxWind.text = StringUtils.formatWind(forecastday.day.maxwind_kph.toString())
        viewHolder.tvTotalPrecip.text = StringUtils.formatPrecip(forecastday.day.totalprecip_mm.toString())
        viewHolder.tvChanceRain.text = StringUtils.formatPercentage(forecastday.day.daily_chance_of_rain)
        viewHolder.tvChanceSnow.text = StringUtils.formatPercentage(forecastday.day.daily_chance_of_snow)
    }
}
