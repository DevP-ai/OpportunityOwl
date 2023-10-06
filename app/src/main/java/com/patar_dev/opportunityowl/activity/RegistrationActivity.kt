package com.patar_dev.opportunityowl.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.patar_dev.opportunityowl.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Intent to Login Page
        binding.txtLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

    }
}