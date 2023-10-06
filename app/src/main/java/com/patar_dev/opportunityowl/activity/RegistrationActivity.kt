package com.patar_dev.opportunityowl.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.patar_dev.opportunityowl.databinding.ActivityRegistrationBinding
import com.patar_dev.opportunityowl.viewModel.auth.AuthViewModel

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegistrationBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var  storage: FirebaseStorage
    private lateinit var auth:FirebaseAuth
    private lateinit var database:FirebaseDatabase

     private var imageUri:Uri?=null
    private var selectImage=registerForActivityResult(ActivityResultContracts.GetContent()){
        imageUri=it

        binding.userImage.setImageURI(imageUri)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialize AuthViewModel
        authViewModel=ViewModelProvider(this)[AuthViewModel::class.java]

        storage= FirebaseStorage.getInstance()
        auth=FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance()


        //Pick Profile Image
        binding.userImage.setOnClickListener {
            selectImage.launch("image/*")
        }

        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        //Intent to Login Page
        binding.txtLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

    }

    private fun uploadImage(imageUri: Uri) {
         val storage=storage.getReference("Profile")
             .child(auth.currentUser!!.uid)
             .child("Profile.jpg")

        storage.putFile(imageUri!!)
            .addOnSuccessListener {
                storage.downloadUrl
                    .addOnSuccessListener {image ->
                        saveData(image.toString())
                    }
            }
    }


    private fun saveData(image: String) {
        val  name=binding.userName.text.toString()
        val email=binding.userEmail.text.toString()
        val password=binding.userPassword.text.toString()
        val profession=binding.userProfession.text.toString()
       authViewModel.saveData(name,email,password,image, profession)
    }


    private fun registerUser() {
        val email=binding.userEmail.text.toString()
        val password=binding.userPassword.text.toString()

        authViewModel.registration(email,password)
            .observe(this, Observer {success->
                if(success){
                    imageUri?.let { uploadImage(it) }
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this,"Sign Up Failed",Toast.LENGTH_SHORT).show()
                }

            })
    }
}