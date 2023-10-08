package com.patar_dev.opportunityowl.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.patar_dev.opportunityowl.R
import com.patar_dev.opportunityowl.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = Navigation.findNavController(this, R.id.hostFragment)
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
    }

    /*
    This MainActivity is Intent From The Registration Activity.So,When User click OnBackPress it goes back to the previous Activity
    To Handle this issue below code implement using NacController to check current user position.If Current user position
    is in Home Fragment then destroy the App otherwise it will goes back to previous Fragment
    */

    override fun onBackPressed() {
        val nacController=Navigation.findNavController(this,R.id.hostFragment)
        val currentDestination=nacController.currentDestination
        if(currentDestination?.id==R.id.hostFragment){

        }else{
            super.onBackPressed()
        }

    }


}