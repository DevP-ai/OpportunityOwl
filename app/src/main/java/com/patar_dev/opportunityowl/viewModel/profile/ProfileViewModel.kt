package com.patar_dev.opportunityowl.viewModel.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.patar_dev.opportunityowl.model.userData.UserData

class ProfileViewModel:ViewModel() {
    private val auth=FirebaseAuth.getInstance()
    private val dbReference:DatabaseReference=FirebaseDatabase.getInstance().getReference("Users")

    private val _userProfile = MutableLiveData<UserData>()
    val userProfile: LiveData<UserData>
        get() = _userProfile

    init {
        fetchData()
    }

    private fun fetchData() {
        val userId = auth.currentUser?.uid

        userId?.let {
            dbReference.child(userId).addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userProfile=snapshot.getValue(UserData::class.java)
                    userProfile?.let {
                        _userProfile.value=it
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}