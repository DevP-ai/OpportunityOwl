package com.patar_dev.opportunityowl.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.patar_dev.opportunityowl.R
import com.patar_dev.opportunityowl.activity.DescriptionActivity
import com.patar_dev.opportunityowl.adapter.JobListAdapter
import com.patar_dev.opportunityowl.databinding.FragmentJobBinding
import com.patar_dev.opportunityowl.viewModel.job.JobViewModel


class JobFragment : Fragment() {
    private lateinit var binding: FragmentJobBinding

    private lateinit var jobsViewModel: JobViewModel
    private lateinit var jobListAdapter: JobListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        jobsViewModel = ViewModelProvider(this)[JobViewModel::class.java]
        jobListAdapter = JobListAdapter(emptyList(),this)


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
         binding.lineProgressBar.visibility=View.VISIBLE
        jobsViewModel.userJobPost.observe(viewLifecycleOwner, { jobList ->
            jobListAdapter = JobListAdapter(jobList,this)
            binding.jobRecyclerView.adapter = jobListAdapter
            binding.lineProgressBar.visibility=View.GONE
        })
        
    }

    fun onItemClick(id: String) {
        val intent=Intent(activity,DescriptionActivity::class.java)
        intent.putExtra("ID",id)
        startActivity(intent)
//        val action=R.id.action_jobFragment_to_jobDescriptionFragment22
//       findNavController().navigate(action)
    }



    companion object{
        const val ID="com.patar_dev.opportunityowl.fragment.uid"
    }
}