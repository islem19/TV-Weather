package dz.islem.weatherapp.util

class StringUtils {
    companion object {
        fun formatLocation(city: String?, country: String?): String? {
            return "$city, $country"
        }

        fun formatTemperature(temperature: String): String? {
            return "$temperature °C"
        }

        fun formatUrl(url: String?): String? {
            return "https:$url"
        }

        fun formatWind(wind: String): String? {
            return "$wind Kph"
        }

        fun formatPrecip(precip: String): String? {
            return "$precip mm"
        }

        fun formatPercentage(str: String): String? {
            return "$str %"
        }

        fun formatTime(time: String): String? {
            return time.split(" ")[1]
        }

    }
}
