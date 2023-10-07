package com.patar_dev.opportunityowl.viewModel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.patar_dev.opportunityowl.model.postmodel.PostModel


class HomeViewModel:ViewModel() {
    private val auth= FirebaseAuth.getInstance()
    private val dbReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("UserPost")

    private val _userPost = MutableLiveData<List<PostModel>>()

    val userPost: MutableLiveData<List<PostModel>>
        get() = _userPost

    init {
        fetchData()
    }

    private fun fetchData() {
         dbReference.addValueEventListener(object :ValueEventListener{
             override fun onDataChange(snapshot: DataSnapshot) {
                 val postList = mutableListOf<PostModel>()
                 for(snap in snapshot.children){
                     val postData=snap.getValue(PostModel::class.java)
                     postData?.let {
                         postList.add(it)
                     }
                 }
                 _userPost.value=postList
             }

             override fun onCancelled(error: DatabaseError) {
                 TODO("Not yet implemented")
             }

         })
    }
}