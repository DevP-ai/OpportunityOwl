package com.patar_dev.opportunityowl.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.patar_dev.opportunityowl.Applications
import com.patar_dev.opportunityowl.databinding.ActivityApplicationBinding

class ApplicationActivity : AppCompatActivity() {
    private lateinit var binding:ActivityApplicationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnApply.setOnClickListener {
            val name = binding.username.text.toString()
            val  email= binding.userEmail.text.toString()
            val  city= binding.jobPostCity.text.toString()
            val  country= binding.jobPostCountry.text.toString()
            val  skills= binding.userSkill.text.toString()
            val  exp= binding.userExperience.text.toString()
            val  coverLetter= binding.jobDescription.text.toString()

            val userId = intent.getStringExtra("userID")
            val applications  = Applications(name,email,city,country,skills,exp,coverLetter)
            FirebaseDatabase.getInstance().getReference("Applications").setValue(applications)
                .addOnSuccessListener {
                    Toast.makeText(this,"Your application successfully submitted",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }

        }
    } }