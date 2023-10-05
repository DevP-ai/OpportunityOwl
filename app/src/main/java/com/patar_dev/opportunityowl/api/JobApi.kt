package com.patar_dev.opportunityowl.api


import com.patar_dev.opportunityowl.model.geoLocationData.Data
import com.patar_dev.opportunityowl.model.geoLocationData.IndiaJobsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface JobApi {

    @Headers( "X-RapidAPI-Key: d0e155d167msha763c0e6cc94c8fp1adc26jsncad05b092969",
        "X-RapidAPI-Host: fresh-linkedin-profile-data.p.rapidapi.com")
    @GET("search-jobs?")
    fun getIndiaJobs(@Query("geo_code")geo_code:Number,@Query("date_posted")date_posted:String):Call<IndiaJobsList>
}