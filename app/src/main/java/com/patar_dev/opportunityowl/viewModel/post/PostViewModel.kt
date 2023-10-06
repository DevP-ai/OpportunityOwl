package com.patar_dev.opportunityowl.viewModel.post

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.patar_dev.opportunityowl.model.postmodel.PostModel
import kotlin.random.Random

class PostViewModel:ViewModel() {
    private val auth=FirebaseAuth.getInstance()
    private val database=FirebaseDatabase.getInstance()

    fun savePost(uid:String,content:String,postImage:String){
       val data=PostModel(uid =auth.currentUser!!.uid,content=content,postImage=postImage)
//
//        database.getReference("UserPost")
//            .child(auth.currentUser!!.uid)
//            .setValue(data)

        val databaseReference = FirebaseDatabase.getInstance().getReference("UserPost")
        val currentUserUid = auth.currentUser!!.uid

// Generate a random key for the new post
        val newPostRef = databaseReference.child(currentUserUid).push()

// Set the value for the new post under the random key
        newPostRef.setValue(data).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Data saved successfully
                // You can access the randomly generated key with newPostRef.key
                val randomKey = newPostRef.key
                // Handle success
            } else {
                // Handle failure
            }
        }

    }
}