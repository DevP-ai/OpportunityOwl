package com.patar_dev.opportunityowl.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.patar_dev.opportunityowl.R
import com.patar_dev.opportunityowl.databinding.ActivityApplicationBinding

class ApplicationActivity : AppCompatActivity() {
    private lateinit var binding:ActivityApplicationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnApply.setOnClickListener {
            Toast.makeText(this,"Your application successfully submitted",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,DescriptionActivity::class.java))
            finish()
        }
    }
}