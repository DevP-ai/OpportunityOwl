package com.patar_dev.opportunityowl.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.rpc.context.AttributeContext.Auth
import com.patar_dev.opportunityowl.R
import com.patar_dev.opportunityowl.databinding.ActivityLoginBinding
import com.patar_dev.opportunityowl.viewModel.auth.AuthViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialize Auth View Model
        authViewModel=ViewModelProvider(this)[AuthViewModel::class.java]

        //Initialize variables


        binding.btnLogin.setOnClickListener {
            if(binding.userEmail.text.toString().isEmpty()){
                binding.userEmail.error="email required"
            }else if(binding.userPassword.text.toString().isEmpty()){
                binding.userPassword.error="password required"
            }else {
                logInUser()
            }
        }
    }

    private fun logInUser() {
        val email=binding.userEmail.text.toString()
        val password=binding.userPassword.text.toString()
         authViewModel.loginUser(email,password).observe(this, Observer {success->
               if(success){
                   startActivity(Intent(this,MainActivity::class.java))
                   finish()
               }else{
                   Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show()
               }
         })
    }
}

