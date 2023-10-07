package com.patar_dev.opportunityowl.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.patar_dev.opportunityowl.adapter.PostAdapter
import com.patar_dev.opportunityowl.databinding.FragmentHomeBinding
import com.patar_dev.opportunityowl.model.postmodel.PostModel
import com.patar_dev.opportunityowl.model.userData.UserData
import com.patar_dev.opportunityowl.viewModel.home.HomeViewModel
import com.patar_dev.opportunityowl.viewModel.post.PostViewModel
import com.patar_dev.opportunityowl.viewModel.profile.ProfileViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var profileViewModel:ProfileViewModel

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var postAdapter: PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database= FirebaseDatabase.getInstance()

        profileViewModel=ViewModelProvider(this)[ProfileViewModel::class.java]
        homeViewModel=ViewModelProvider(this)[HomeViewModel::class.java]

        postAdapter= PostAdapter(emptyList())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.edtPost.setOnClickListener{
            val fragmentManager = requireActivity().supportFragmentManager
            val postFragment = PostFragment()
            postFragment.showFullWidthPopup(fragmentManager)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.userProfile.observe(viewLifecycleOwner,{user->
            Glide.with(this)
                .load(user.profile)
                .into(binding.userProfile)
        })

        homeViewModel.userPost.observe(viewLifecycleOwner,{postList->
             postAdapter=PostAdapter(postList)
             binding.postRecyclerView.adapter=postAdapter
        })
    }
}
