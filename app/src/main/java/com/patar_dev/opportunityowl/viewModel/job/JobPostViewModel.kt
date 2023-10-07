package com.patar_dev.opportunityowl.viewModel.job

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.patar_dev.opportunityowl.model.job.JobModel
import java.util.UUID

class JobPostViewModel:ViewModel(){

    private val database=FirebaseDatabase.getInstance()

    fun saveJob(uid:String,jobTitle:String,companyName:String,location:String,salary:String,description:String,jobImage:String){
        val job=JobModel(
            uid=uid,
            jobTitle=jobTitle,
            companyName=companyName,
            location=location,
            salary=salary,
            description=description,
            jobImage=jobImage
        )
        database.getReference("JobPost").child(uid+ UUID.randomUUID())
            .setValue(job)
    }
}