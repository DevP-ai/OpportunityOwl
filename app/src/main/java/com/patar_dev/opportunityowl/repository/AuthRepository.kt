package com.patar_dev.opportunityowl.repository

import com.google.firebase.auth.FirebaseAuth

class AuthRepository {
    private val auth=FirebaseAuth.getInstance()

    fun getCurrentUser() = auth.currentUser

    fun signOut() {
        auth.signOut()
    }

}