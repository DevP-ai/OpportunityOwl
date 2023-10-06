package com.patar_dev.opportunityowl.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.google.firebase.auth.FirebaseAuth
import com.patar_dev.opportunityowl.R
import com.patar_dev.opportunityowl.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val animation=AnimationUtils.loadAnimation(this,R.anim.slide)
       binding.imgLogo.startAnimation(animation)

       Handler(Looper.myLooper()!!).postDelayed({
           if(FirebaseAuth.getInstance().currentUser !=null){
               startActivity(Intent(this,MainActivity::class.java))
               finish()
           }else {
               startActivity(Intent(this, RegistrationActivity::class.java))
               finish()
           }
       },3000)
    }
}