package com.patar_dev.opportunityowl.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.patar_dev.opportunityowl.R
import com.patar_dev.opportunityowl.databinding.FragmentResourceBinding
import com.patar_dev.opportunityowl.viewModel.IndiaJobsViewModel

class ResourceFragment : Fragment() {
   private lateinit var binding: FragmentResourceBinding
   private lateinit var indiaJobsViewModel: IndiaJobsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        indiaJobsViewModel=IndiaJobsViewModel()
//        indiaJobsViewModel.getIndiaJobList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentResourceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}