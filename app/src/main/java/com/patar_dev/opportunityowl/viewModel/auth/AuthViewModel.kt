package com.patar_dev.opportunityowl.viewModel.auth

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import com.patar_dev.opportunityowl.model.userData.UserData

class AuthViewModel:ViewModel() {
    private val auth=FirebaseAuth.getInstance()
    private val database=FirebaseDatabase.getInstance()
    private val storage=FirebaseStorage.getInstance()

    private var registrationLiveData=MutableLiveData<Boolean>()
    private var loginLiveData=MutableLiveData<Boolean>()

    fun registration(email:String,password:String):LiveData<Boolean>{
       auth.createUserWithEmailAndPassword(email,password)
           .addOnCompleteListener {task->
               if(task.isSuccessful){
                   registrationLiveData.postValue(true)
               }else{
                   registrationLiveData.postValue(false)
               }
           }
        return registrationLiveData
    }

    fun saveData(name:String,email:String,password:String,profile:String,profession:String,phone:String, token : String){

        val data=UserData(
            uid = auth.currentUser!!.uid,
            name = name,
            email=email,
            password=password,
            profile = profile,
            profession = profession,
            phone = phone,
            userToken = token
        )

        database.getReference("Users")
            .child(auth.currentUser!!.uid!!)
            .setValue(data)
    }

    fun loginUser(email: String,password: String):LiveData<Boolean>{
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {task->
                 if(task.isSuccessful){
                     loginLiveData.postValue(true)
                 }else {
                     loginLiveData.postValue(false)
                 }
            }
        return loginLiveData
    }
}