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
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import com.patar_dev.opportunityowl.databinding.ActivityRegistrationBinding
import com.patar_dev.opportunityowl.viewModel.auth.AuthViewModel

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var storage: FirebaseStorage
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase


    //Image Picker
    private var imageUri: Uri? = null
    private var selectImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri = it

        binding.userImage.setImageURI(imageUri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialize AuthViewModel
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        //Initialize Firebase
        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()


        //Pick Profile Image
        binding.userImage.setOnClickListener {
            selectImage.launch("image/*")
        }

        binding.btnRegister.setOnClickListener {
            FirebaseMessaging.getInstance().subscribeToTopic("/topics/jobPost")
            if(binding.userName.text.toString().isEmpty()){
                binding.userName.error="name required"
            }else if(binding.userEmail.text.toString().isEmpty()){
                binding.userEmail.error="email required"
            }else if(binding.userProfession.text.toString().isEmpty()){
                binding.userProfession.error="profession required"
            }else if(binding.userPassword.text.toString().isEmpty()){
                binding.userPassword.error="password required"
            }else if(binding.userConfirmPassword.text.toString().isEmpty()){
                binding.userConfirmPassword.error="confirm password"
            }else if(binding.userPassword.text.toString() != binding.userConfirmPassword.text.toString()){
                binding.userConfirmPassword.error="password not matching"
            }else if(binding.userPassword.text.toString().length<6){
                Toast.makeText(this,"Password length must be greater than six",Toast.LENGTH_SHORT).show()
            }else{
                registerUser()
            }
        }

        //Intent to Login Page
        binding.txtLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    //Upload image to Storage ,download url and call saveData Function with image url to string parameter
    private fun uploadImage(imageUri: Uri) {
        val storage = storage.getReference("Profile")
            .child(auth.currentUser!!.uid)
            .child("Profile.jpg")

        storage.putFile(imageUri!!)
            .addOnSuccessListener {
                storage.downloadUrl
                    .addOnSuccessListener { image ->
                        saveData(image.toString())
                    }
            }
    }

    //Save Data in Firebase Database
    private fun saveData(image: String) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener{ task ->
            if(!task.isSuccessful){
                return@addOnCompleteListener
            }
            val token = task.result
            val name = binding.userName.text.toString()
            val email = binding.userEmail.text.toString()
            val password = binding.userPassword.text.toString()
            val profession = binding.userProfession.text.toString()
            val num=binding.userNumber.text.toString()
            val phone="+91${num}"
            authViewModel.saveData(name, email, password, image, profession,phone,token)
        }

    }

    //User Register With Email and Password and also call the above  uploadImage function
    private fun registerUser() {
        val email = binding.userEmail.text.toString()
        val password = binding.userPassword.text.toString()
        authViewModel.registration(email, password)
            .observe(this, Observer { success ->
                if (success) {
                    imageUri?.let { uploadImage(it) }  //Call uploadImage function
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Sign Up Failed", Toast.LENGTH_SHORT).show()
                }

            })
    }
}