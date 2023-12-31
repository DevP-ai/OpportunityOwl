package com.patar_dev.opportunityowl.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.patar_dev.opportunityowl.JobFilter
import com.patar_dev.opportunityowl.databinding.JobItemBinding
import com.patar_dev.opportunityowl.fragment.JobFragment
import com.patar_dev.opportunityowl.model.job.JobModel

class JobListAdapter( val itemClickListener: JobFragment):RecyclerView.Adapter<JobListAdapter.JobListViewHolder>() ,
    Filterable {


    var jobList = ArrayList<JobModel>()
    var originalList = ArrayList<JobModel>()

    fun setJobList(jobList: List<JobModel>) {
        this.jobList = jobList as ArrayList<JobModel>
        this.originalList = ArrayList(jobList)
        notifyDataSetChanged()
    }

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

        val type=jobList[position].jobType
        val title=jobList[position].jobTitle
        holder.binding.jobTitle.text="${title}(${type})"
        holder.binding.companyName.text=jobList[position].companyName
        val city=jobList[position].city
        val country=jobList[position].country
        holder.binding.location.text="${city},${country}"


        holder.itemView.setOnClickListener {
            val job = jobList[position]
            job.uid?.let { id -> itemClickListener.onItemClick(id) }
        }

    }
    private var filter : JobFilter? = null
    override fun getFilter(): Filter {
        if(filter == null) return JobFilter(this,originalList)
        return  filter as JobFilter
    }
}