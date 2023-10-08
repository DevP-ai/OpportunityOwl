package com.patar_dev.opportunityowl.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.patar_dev.opportunityowl.databinding.JobItemBinding
import com.patar_dev.opportunityowl.fragment.JobFragment
import com.patar_dev.opportunityowl.model.job.JobModel

class JobListAdapter(private val jobList: List<JobModel>, private val itemClickListener: JobFragment):RecyclerView.Adapter<JobListAdapter.JobListViewHolder>() {


    inner class JobListViewHolder(var binding: JobItemBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobListViewHolder {
        return JobListViewHolder(JobItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    override fun onBindViewHolder(holder: JobListViewHolder, position: Int) {
         Glide.with(holder.itemView)
             .load(jobList[position].jobImage)
             .into(holder.binding.companyLogo)

        holder.binding.jobTitle.text=jobList[position].jobTitle
        holder.binding.companyName.text=jobList[position].companyName
        holder.binding.location.text=jobList[position].city


        holder.itemView.setOnClickListener {
            val job = jobList[position]
            job.uid?.let { id -> itemClickListener.onItemClick(id) }
        }

    }
}