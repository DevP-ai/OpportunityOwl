package com.patar_dev.opportunityowl.api.job

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApiInstance {
    private var api: JobApi?=null

    fun getApi(): JobApi {
        if(api ==null){
            api = createApi()
        }
        return api!!
    }

    private fun createApi(): JobApi? {
       val retrofit=Retrofit.Builder()
           .baseUrl("https://fresh-linkedin-profile-data.p.rapidapi.com/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()

        return retrofit.create(JobApi::class.java)
    }
}