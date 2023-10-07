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
import com.google.firebase.storage.FirebaseStorage
import com.patar_dev.opportunityowl.R
import com.patar_dev.opportunityowl.databinding.FragmentJobUpdateBinding
import com.patar_dev.opportunityowl.viewModel.job.JobPostViewModel
import java.util.UUID

class JobUpdateFragment : Fragment() {
    private lateinit var binding:FragmentJobUpdateBinding
    private lateinit var storage: FirebaseStorage
    private lateinit var jobPostViewModel: JobPostViewModel

    private var imageUri:Uri?=null
    private var selectImage=registerForActivityResult(ActivityResultContracts.GetContent()){
        imageUri=it

        binding.jobImagePost.setImageURI(imageUri)
        jobPostViewModel=ViewModelProvider(this)[JobPostViewModel::class.java]

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        storage=FirebaseStorage.getInstance()
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
        val uid=FirebaseAuth.getInstance().currentUser!!.uid
        val title=binding.jobPostTitle.text.toString()
        val company=binding.jobPostCompanyName.text.toString()
        val location=binding.jobPostLocation.text.toString()
        val salary=binding.jobPostSalary.text.toString()
        val description=binding.jobDescription.text.toString()
        jobPostViewModel.saveJob(uid,title,company,location,salary,description,image)

        binding.lineProgressBar.visibility=View.GONE
        findNavController().navigate(R.id.action_jobUpdateFragment_to_jobFragment)
    }

}