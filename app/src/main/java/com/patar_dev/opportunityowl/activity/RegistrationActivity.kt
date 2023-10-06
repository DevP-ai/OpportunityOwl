package com.patar_dev.opportunityowl.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.patar_dev.opportunityowl.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegistrationBinding

    private var imageUri:Uri?=null
    private var selectImage=registerForActivityResult(ActivityResultContracts.GetContent()){
        imageUri=it

        binding.userImage.setImageURI(imageUri)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Pick Profile Image
        binding.userImage.setOnClickListener {
            selectImage.launch("image/*")
        }

        //Intent to Login Page
        binding.txtLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

    }
}