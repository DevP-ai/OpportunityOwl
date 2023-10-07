package com.patar_dev.opportunityowl.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.patar_dev.opportunityowl.databinding.JobItemBinding
import com.patar_dev.opportunityowl.model.geoLocationData.Data

class JobListAdapter:RecyclerView.Adapter<JobListAdapter.JobListViewHolder>() {
    private var jobList = ArrayList<Data>()

    fun setJob(jobList: ArrayList<Data>) {
        this.jobList = jobList
        notifyDataSetChanged()
    }

    inner class JobListViewHolder(val binding: JobItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobListViewHolder {
        return JobListViewHolder(
            JobItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    override fun onBindViewHolder(holder: JobListViewHolder, position: Int) {
        holder.binding.jobTitle.text = jobList[position].job_title
        holder.binding.companyName.text = jobList[position].company
        holder.binding.location.text = jobList[position].location
    }
}