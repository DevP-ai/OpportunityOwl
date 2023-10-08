package com.patar_dev.opportunityowl.viewModel.job

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.patar_dev.opportunityowl.api.job.notifications.NotificationUtilities
import com.patar_dev.opportunityowl.model.job.JobModel
import com.patar_dev.opportunityowl.model.notification.NotificationData
import com.patar_dev.opportunityowl.model.notification.PushNotification
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.UUID

class JobPostViewModel:ViewModel(){

    private val database=FirebaseDatabase.getInstance()

    fun saveJob(uid:String,jobTitle:String,companyName:String,city:String,country:String,
                skills:String,experience:String,jobType:String,salary:String,description:String,
                jobImage:String,hrName:String,
                hrProfession:String,hrImage:String){
        val job=JobModel(
            uid=uid,
            jobTitle=jobTitle,
            companyName=companyName,
            city =city,
            country=country,
            skills=skills,
            experience=experience,
            jobType=jobType,
            salary=salary,
            description=description,
            jobImage=jobImage,
            hrName=hrName,
            hrProfession=hrProfession,
            hrImage=hrImage
        )
        database.getReference("JobPost").child(uid)
            .setValue(job).addOnCompleteListener{
                Log.d("tt" , "Saevd")
                sendNotification(companyName , city , jobTitle , description)
            }
    }

    private fun sendNotification(
        companyName: String,
        location: String,
        jobTitle: String,
        description: String
    ) {
        NotificationUtilities.api.sendNotification(PushNotification(NotificationData(
            companyName = companyName , location = location , jobTitle = jobTitle , description = description
        ), "/topics/jobPost"))
            .enqueue(object  : Callback<PushNotification>{
                override fun onResponse(
                    call: Call<PushNotification>,
                    response: Response<PushNotification>
                ) {
                    if (response.isSuccessful){
                        Log.d("no" , "send")
                    }
                }

                override fun onFailure(call: Call<PushNotification>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}