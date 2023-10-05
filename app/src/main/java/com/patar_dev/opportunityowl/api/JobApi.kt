package com.patar_dev.opportunityowl.api


import com.patar_dev.opportunityowl.model.geoLocationData.Data
import com.patar_dev.opportunityowl.model.geoLocationData.IndiaJobsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface JobApi {


    @GET("search-jobs?")
    fun getIndiaJobs(@Query("geo_code")geo_code:Number,@Query("date_posted")date_posted:String):Call<IndiaJobsList>
}