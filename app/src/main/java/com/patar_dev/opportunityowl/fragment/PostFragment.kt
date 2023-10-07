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
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.patar_dev.opportunityowl.activity.MainActivity
import com.patar_dev.opportunityowl.databinding.FragmentPostBinding
import java.util.UUID

class PostFragment :DialogFragment() {
    private lateinit var binding:FragmentPostBinding
    private lateinit var storage:FirebaseStorage
    private  var imageUri:Uri?=null
    private var selectImage=registerForActivityResult(ActivityResultContracts.GetContent()){
        imageUri=it
        binding.imagePost.setImageURI(imageUri)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        storage=FirebaseStorage.getInstance()

    }
    fun showFullWidthPopup(fragmentManager: FragmentManager) {
        show(fragmentManager, "post_fragment_tag")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentPostBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.imgFab.setOnClickListener {
            selectImage.launch("image/*")
        }

        binding.postButton.setOnClickListener {
            if(binding.edtPost.text.toString().isEmpty()){

            }else{
                imageUri!!.let {uploadImage(it)  }
            }
        }


    }

    private fun uploadImage(imageUri: Uri) {
        val storage=storage.getReference("UserPostImage").child(UUID.randomUUID().toString())
        storage.putFile(imageUri!!)
            .addOnSuccessListener {
                storage.downloadUrl
                    .addOnSuccessListener {image->

                    }
            }

    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        // Set custom attributes to make it appear as a full-width pop-up
        val window = dialog.window
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        window?.setBackgroundDrawableResource(android.R.color.transparent)

        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setGravity(Gravity.TOP) // Place the dialog at the top
    }

}