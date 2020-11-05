package dz.islem.weatherapp.data.remote.api

import dz.islem.weatherapp.data.remote.model.location.Location
import io.reactivex.Single
import retrofit2.http.GET




interface LocationApi {

    @GET("/json")
    fun getLocation(): Single<Location>
}