package com.patar_dev.opportunityowl.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.patar_dev.opportunityowl.adapter.JobListAdapter
import com.patar_dev.opportunityowl.databinding.FragmentJobBinding
import com.patar_dev.opportunityowl.model.geoLocationData.Data
import com.patar_dev.opportunityowl.viewModel.IndiaJobsViewModel

class JobFragment : Fragment() {
    private lateinit var binding: FragmentJobBinding
    private lateinit var indiaJobsViewModel: IndiaJobsViewModel
    private lateinit var jobListAdapter: JobListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        indiaJobsViewModel= IndiaJobsViewModel()
        jobListAdapter= JobListAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentJobBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jobRecyclerView()
        indiaJobsViewModel.getAllJobs()
        indiaJobObserver()
    }


    private fun indiaJobObserver() {
        indiaJobsViewModel.observeIndiaJobs().observe(viewLifecycleOwner){
            jobListAdapter.setJob(it as ArrayList<Data>)
        }
    }

    private fun jobRecyclerView() {
        binding.jobRecyclerView.apply {
            layoutManager= LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            adapter=jobListAdapter
        }
    }
}