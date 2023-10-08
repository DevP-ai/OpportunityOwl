package com.patar_dev.opportunityowl.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.patar_dev.opportunityowl.R
import com.patar_dev.opportunityowl.activity.DescriptionActivity
import com.patar_dev.opportunityowl.adapter.JobListAdapter
import com.patar_dev.opportunityowl.databinding.FragmentJobBinding
import com.patar_dev.opportunityowl.model.job.JobModel
import com.patar_dev.opportunityowl.viewModel.job.JobViewModel


class JobFragment : Fragment() {
    private lateinit var binding: FragmentJobBinding

    private lateinit var jobsViewModel: JobViewModel
    private lateinit var jobListAdapter: JobListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jobsViewModel = ViewModelProvider(this)[JobViewModel::class.java]
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
        prepareRvForJobFragment()

         binding.lineProgressBar.visibility=View.VISIBLE
        jobsViewModel.userJobPost.observe(viewLifecycleOwner) { jobList ->
           jobListAdapter.setJobList(jobList)
            binding.lineProgressBar.visibility=View.GONE
        }
        binding.searchJob.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()
                jobListAdapter.filter.filter(query)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

    }

    private fun prepareRvForJobFragment() {
        jobListAdapter   = JobListAdapter(this)
        binding.jobRecyclerView.apply {
            adapter = jobListAdapter
        }
    }

    fun onItemClick(id: String) {
        val intent=Intent(activity,DescriptionActivity::class.java)
        intent.putExtra("ID",id)
        startActivity(intent)
    }



    companion object{
        const val ID="com.patar_dev.opportunityowl.fragment.uid"
    }
}