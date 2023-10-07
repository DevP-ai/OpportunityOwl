package com.patar_dev.opportunityowl.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.patar_dev.opportunityowl.R
import com.patar_dev.opportunityowl.databinding.FragmentJobUpdateBinding
import com.patar_dev.opportunityowl.viewModel.job.JobPostViewModel
import com.patar_dev.opportunityowl.viewModel.profile.ProfileViewModel
import java.util.UUID

class JobUpdateFragment : Fragment() {
    private lateinit var binding:FragmentJobUpdateBinding
    private lateinit var storage: FirebaseStorage
    private lateinit var jobPostViewModel: JobPostViewModel
    private var auth=FirebaseAuth.getInstance()

    private var hrName:String?=""
    private var hrProfession:String?=""
    private var hrImage:String?=""

    private lateinit var profileViewModel: ProfileViewModel

    private var imageUri:Uri?=null
    private var selectImage=registerForActivityResult(ActivityResultContracts.GetContent()){
        imageUri=it

        binding.jobImagePost.setImageURI(imageUri)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        storage=FirebaseStorage.getInstance()

        jobPostViewModel=ViewModelProvider(this)[JobPostViewModel::class.java]
        profileViewModel=ViewModelProvider(this)[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentJobUpdateBinding.inflate(layoutInflater)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.jobImgFab.setOnClickListener {
            selectImage.launch("image/*")
        }

        binding.postJobButton.setOnClickListener {
            binding.lineProgressBar.visibility=View.VISIBLE
            imageUri!!.let {uploadImage(it)  }
        }

        profileViewModel.userProfile.observe(viewLifecycleOwner,{
            hrName=it.name.toString()
            hrProfession=it.profession.toString()
            hrImage=it.profile.toString()
        })
    }

    private fun uploadImage(imageUri: Uri) {
        val storage=storage.getReference("JobPostImage").child(UUID.randomUUID().toString())
        storage.putFile(imageUri!!)
            .addOnSuccessListener {
                storage.downloadUrl
                    .addOnSuccessListener {image->
                        savePost(image.toString())
                    }
            }

    }


    private fun savePost(image: String) {
        val uid=FirebaseAuth.getInstance().currentUser!!.uid+UUID.randomUUID()
        val title=binding.jobPostTitle.text.toString()
        val company=binding.jobPostCompanyName.text.toString()
        val location=binding.jobPostLocation.text.toString()
        val salary=binding.jobPostSalary.text.toString()
        val description=binding.jobDescription.text.toString()

        hrName?.let {
            hrProfession?.let { it1 ->
                hrImage?.let { it2 ->
                    jobPostViewModel.saveJob(uid,title,company,location,salary,description,image,
                        it, it1, it2
                    )
                }
            }
        }

        binding.lineProgressBar.visibility=View.GONE
        findNavController().navigate(R.id.action_jobUpdateFragment_to_jobFragment)
    }

}