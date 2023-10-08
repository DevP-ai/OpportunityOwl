package com.patar_dev.opportunityowl.api.job


import com.patar_dev.opportunityowl.model.geoLocationData.Data
import com.patar_dev.opportunityowl.model.geoLocationData.IndiaJobsList
import com.patar_dev.opportunityowl.model.notification.PushNotification
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface JobApi {

    @GET("search-jobs?")
    fun getIndiaJobs(@Query("geo_code")geo_code:Number,@Query("date_posted")date_posted:String):Call<IndiaJobsList>

    // for notification
    @Headers(
        "Content-Type: application/json",
        "Authorization: key=AAAALFRhlGc:APA91bHKdUaIp1zoNg6wb5MBS3kD0vkypsUEEhUdx2bcFyh08TKg1eP06WPfj7GE9q-K0BDHHcuDZNT6-QsONTr4K3191tY3AnMGvfAkuqqYxhkvf17SK2PSsUH9F8B5rnAxhsQMTSc1"
    )
    @POST("fcm/send")
    fun sendNotification(@Body notification: PushNotification): Call<PushNotification>
}