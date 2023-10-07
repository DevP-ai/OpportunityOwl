package com.patar_dev.opportunityowl.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.patar_dev.opportunityowl.R
import com.patar_dev.opportunityowl.databinding.FragmentProfileBinding
import com.patar_dev.opportunityowl.viewModel.profile.ProfileViewModel

class ProfileFragment : Fragment() {
      private lateinit var binding:FragmentProfileBinding
      private lateinit var profileViewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        profileViewModel=ViewModelProvider(this)[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.userProfile.observe(viewLifecycleOwner,{user->
             binding.profileName.text=user.name
            binding.profileProfession.text=user.profession
             Glide.with(this)
                 .load(user.profile)
                 .into(binding.profilePhoto)
        })
    }
    companion object {

    }
}