package com.patar_dev.opportunityowl.viewModel.post

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.patar_dev.opportunityowl.model.postmodel.PostModel
import java.util.UUID

class PostViewModel:ViewModel() {
    private  var database=FirebaseDatabase.getInstance()
    fun savePost(uid:String,content:String,postImage:String,userName:String,userProfession:String,userProfile:String){
        val post=PostModel(
            uid=uid,
            content=content,
            postImage=postImage,
            userName=userName,
            userProfession=userProfession,
            userProfile=userProfile
        )
        database.getReference("UserPost").child(uid+UUID.randomUUID())
            .setValue(post)
    }
}