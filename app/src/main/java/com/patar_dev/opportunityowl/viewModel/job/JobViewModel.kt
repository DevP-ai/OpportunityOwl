package com.patar_dev.opportunityowl.viewModel.job

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.patar_dev.opportunityowl.model.job.JobModel
import com.patar_dev.opportunityowl.model.postmodel.PostModel

class JobViewModel:ViewModel() {
    private val auth= FirebaseAuth.getInstance()
    private val dbReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("JobPost")

    private val _userJobPost = MutableLiveData<List<JobModel>>()
    val userJobPost: MutableLiveData<List<JobModel>>
        get() = _userJobPost
    init {
        fetchData()
    }

    private fun fetchData() {
        dbReference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val jobList = mutableListOf<JobModel>()
                for(snap in snapshot.children){
                    val jobData=snap.getValue(JobModel::class.java)
                    jobData?.let {
                        jobList.add(it)
                    }
                }
                _userJobPost.value=jobList
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}