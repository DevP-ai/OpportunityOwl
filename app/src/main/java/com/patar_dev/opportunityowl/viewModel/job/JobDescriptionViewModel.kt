package com.patar_dev.opportunityowl.viewModel.job

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.patar_dev.opportunityowl.model.job.JobModel
import com.patar_dev.opportunityowl.model.userData.UserData

class JobDescriptionViewModel:ViewModel() {
    private val auth= FirebaseAuth.getInstance()
    private val dbReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("JobPost")

    private val _jobDescription = MutableLiveData<JobModel>()
    val jobDescription: LiveData<JobModel>
        get() = _jobDescription



    fun fetchData(uid:String) {
         dbReference.child(uid).addListenerForSingleValueEvent(object :ValueEventListener{
             override fun onDataChange(snapshot: DataSnapshot) {
                 val description=snapshot.getValue(JobModel::class.java)
                 description?.let {
                     _jobDescription.value=it
                 }
             }

             override fun onCancelled(error: DatabaseError) {
                 TODO("Not yet implemented")
             }

         })
    }


}