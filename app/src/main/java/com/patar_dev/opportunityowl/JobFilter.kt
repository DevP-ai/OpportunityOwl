package com.patar_dev.opportunityowl

import android.widget.Filter
import com.patar_dev.opportunityowl.adapter.JobListAdapter
import com.patar_dev.opportunityowl.model.job.JobModel
import java.util.Locale

class JobFilter(
    private val adapter: JobListAdapter,
    private val filterJobs : ArrayList<JobModel>
) : Filter() {
    override fun performFiltering(searchingText: CharSequence?): FilterResults {
        val filteredResults = FilterResults()

        if(!searchingText.isNullOrEmpty()){
            val query = searchingText.toString().trim().uppercase(Locale.getDefault()).split(" ")
            val filteredJobList = ArrayList<JobModel>()
            for(jobs in filterJobs){

                if(query.any { search ->
                        jobs.companyName?.uppercase(Locale.getDefault())?.contains(search) == true ||
                                jobs.jobTitle?.uppercase(Locale.getDefault())?.contains(search) == true ||
                                jobs.city?.uppercase(Locale.getDefault())?.contains(search) == true

                    }){
                    filteredJobList.add(jobs)
                }
            }
            filteredResults.apply {
                count = filteredJobList.size
                values = filteredJobList
            }

        }
        else{
            filteredResults.apply {
                count = filterJobs.size
                values = filterJobs
            }
        }

        return filteredResults
    }

    override fun publishResults(p0: CharSequence?, results: FilterResults?) {
        adapter.jobList = results?.values as ArrayList<JobModel>
        adapter.notifyDataSetChanged()
    }
}