package dz.islem.weatherapp.data.local

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dz.islem.weatherapp.App


object LocationSharedPrefs {

    private var sharedPreferences: SharedPreferences
    private val CITY_KEY = "county"
    private val MY_PREFS_NAME = "weather_pref"

    init {
        sharedPreferences = App.instance.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
    }

    fun setDefaultCity(city: String?) {
        sharedPreferences.edit()
            .putString(CITY_KEY, city)
            .apply()
    }

    fun getDefaultCity(): String? {
        return sharedPreferences.getString(CITY_KEY, null)
    }
}