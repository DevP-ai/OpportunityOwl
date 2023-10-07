package com.patar_dev.opportunityowl.viewModel.job

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.patar_dev.opportunityowl.model.job.JobModel
import java.util.UUID

class JobPostViewModel:ViewModel(){

    private val database=FirebaseDatabase.getInstance()

    fun saveJob(uid:String,jobTitle:String,companyName:String,location:String,
                salary:String,description:String,jobImage:String,hrName:String,hrProfession:String,hrImage:String){
        val job=JobModel(
            uid=uid,
            jobTitle=jobTitle,
            companyName=companyName,
            location=location,
            salary=salary,
            description=description,
            jobImage=jobImage,
            hrName=hrName,
            hrProfession=hrProfession,
            hrImage=hrImage
        )
        database.getReference("JobPost").child(uid)
            .setValue(job)
    }
}