# TV-Weather -Android TV App-

This is a sample Android TV app, that fetches weather data from [Weather API](https://www.weatherapi.com), you can customize and use the app with your own weather api, see below how to set it up.

## Screenshots
<br><br>
[<img src="screenshots/screenshot1.gif" align="center"
width="790"
    hspace="10" vspace="10">](screenshots/screenshot1.gif)
<br><br>
[<img src="screenshots/screenshot2.gif" align="center"
width="790"
    hspace="10" vspace="10">](screenshots/screenshot2.gif)

## Setup
Clone this repository and import into **Android Studio**
```bash
git clone https://github.com/islem19/TV-Weather.git
```
## Configs in gradle.properties
Sample Configuration

```gradle
android.useAndroidX=true
android.enableJetifier=true

WEATHER_API_URL="https://api.weatherapi.com/v1/"
WEATHER_API_KEY="<Your_Weather_Api_Key>"
LOCATION_API_URL="http://ip-api.com"
```

Check the [Weather API website](https://www.weatherapi.com/) to see how to setup your own account.

## Build Configurations in build.gradle

Change the configurations accordingly in the different settings in buildTypes For example,
```gradle
buildTypes {
        debug {
            buildConfigField "String", "WEATHER_API_URL", WEATHER_API_URL
            buildConfigField "String", "WEATHER_API_KEY", WEATHER_API_KEY
            buildConfigField "String", "LOCATION_API_URL", LOCATION_API_URL
        }
    }
```

## Permissions
The App requires the following permissions:
- Internet access.

## Libraries and Dependencies
- [Kotlin](https://kotlinlang.org/)
- [Glide](https://github.com/bumptech/glide)
- [Retrofit](https://square.github.io/retrofit/)
- [RxJava](https://github.com/ReactiveX/RxAndroid)
- [Kotlin Serializer](https://github.com/Kotlin/kotlinx.serialization)
- [Lottie](https://airbnb.design/lottie/)
- [CardView](https://developer.android.com/jetpack/androidx/releases/cardview)
- [RecyclerView](https://developer.android.com/jetpack/androidx/releases/recyclerview)

## Maintainers
This project is mantained by:
* [Abdelkader Sellami](https://github.com/islem19)


## Contributing

1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
4. Push your branch (git push origin my-new-feature)
5. Create a new Pull Request


## License
This application is released under GNU GPLv3 (see [LICENSE](LICENSE)). Some of the used libraries are released under different licenses.
