package com.patar_dev.opportunityowl.api.job.notifications

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NotificationUtilities {

    val api by lazy{
        Retrofit.Builder()
            .baseUrl("https://fcm.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NotificationInterface::class.java)
    }
}