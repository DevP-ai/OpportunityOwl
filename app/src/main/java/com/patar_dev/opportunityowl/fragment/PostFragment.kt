package com.patar_dev.opportunityowl.fragment

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.patar_dev.opportunityowl.R
import com.patar_dev.opportunityowl.activity.MainActivity
import com.patar_dev.opportunityowl.databinding.FragmentPostBinding
import com.patar_dev.opportunityowl.viewModel.post.PostViewModel
import com.patar_dev.opportunityowl.viewModel.profile.ProfileViewModel
import java.util.UUID

class PostFragment : Fragment() {
    private lateinit var binding: FragmentPostBinding
    private lateinit var storage: FirebaseStorage
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var postViewModel: PostViewModel
    private var name: String? = ""
    private var profession: String? = ""
    private var profile: String? = ""

    private var imageUri: Uri? = null
    private var selectImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri = it
        binding.imagePost.setImageURI(imageUri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        storage = FirebaseStorage.getInstance()
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        postViewModel = ViewModelProvider(this)[PostViewModel::class.java]


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPostBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.imgFab.setOnClickListener {
            selectImage.launch("image/*")
        }


        profileViewModel.userProfile.observe(viewLifecycleOwner, { user ->
            Glide.with(this)
                .load(user.profile)
                .into(binding.postProfile)

            binding.postName.text = user.name
            binding.postProfession.text = user.profession

            name = user.name
            profession = user.profession
            profile = user.profile

        })

        binding.postButton.setOnClickListener {
            if (binding.edtPost.text.toString().isEmpty()) {

            } else {
                imageUri!!.let { uploadImage(it) }
            }
        }


    }

    private fun uploadImage(imageUri: Uri) {
        val storage = storage.getReference("UserPostImage").child(UUID.randomUUID().toString())
        storage.putFile(imageUri!!)
            .addOnSuccessListener {
                storage.downloadUrl
                    .addOnSuccessListener { image ->
                        savePost(image.toString())
                    }
            }

    }

    private fun savePost(image: String) {
        val content = binding.edtPost.text.toString()
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        profession?.let { name?.let { it1 -> profile?.let { it2 ->
                    postViewModel.savePost(uid, content, image, it1, it, it2) }
            } }
        findNavController().navigate(R.id.action_postFragment_to_homeFragment2)
    }

}