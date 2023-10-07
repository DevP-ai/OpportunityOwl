package com.patar_dev.opportunityowl.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.patar_dev.opportunityowl.R
import com.patar_dev.opportunityowl.databinding.ActivityDescriptionBinding
import com.patar_dev.opportunityowl.fragment.JobFragment
import com.patar_dev.opportunityowl.model.job.JobModel
import com.patar_dev.opportunityowl.viewModel.job.JobDescriptionViewModel

class DescriptionActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDescriptionBinding
    private lateinit var jobDescriptionViewModel: JobDescriptionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val uid=intent.getStringExtra("ID")

        jobDescriptionViewModel=ViewModelProvider(this)[JobDescriptionViewModel::class.java]
        jobDescriptionViewModel.fetchData(uid.toString())

        jobDescriptionViewModel.jobDescription.observe(this,{
            binding.title.text=it.jobTitle
        })


    }
}