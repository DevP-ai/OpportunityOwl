package com.patar_dev.opportunityowl.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.patar_dev.opportunityowl.R
import com.patar_dev.opportunityowl.adapter.JobListAdapter
import com.patar_dev.opportunityowl.databinding.FragmentJobBinding
import com.patar_dev.opportunityowl.model.geoLocationData.Data
import com.patar_dev.opportunityowl.viewModel.IndiaJobsViewModel
import com.patar_dev.opportunityowl.viewModel.job.JobViewModel

class JobFragment : Fragment() {
    private lateinit var binding: FragmentJobBinding

    private lateinit var jobsViewModel: JobViewModel
    private lateinit var jobListAdapter: JobListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        jobsViewModel = ViewModelProvider(this)[JobViewModel::class.java]
        jobListAdapter = JobListAdapter(emptyList())


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentJobBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.jobUpdateFab.setOnClickListener {
            findNavController().navigate(R.id.action_jobFragment_to_jobUpdateFragment)
        }

        jobsViewModel.userJobPost.observe(viewLifecycleOwner, { jobList ->
            jobListAdapter = JobListAdapter(jobList)
            binding.jobRecyclerView.adapter = jobListAdapter
        })
    }




}