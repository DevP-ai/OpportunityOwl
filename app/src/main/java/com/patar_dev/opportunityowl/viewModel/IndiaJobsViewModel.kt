package com.patar_dev.opportunityowl.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.patar_dev.opportunityowl.api.RetrofitApiInstance
import com.patar_dev.opportunityowl.model.geoLocationData.Data
import com.patar_dev.opportunityowl.model.geoLocationData.IndiaJobsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IndiaJobsViewModel : ViewModel() {
    private var indiaJobLiveData = MutableLiveData<List<Data>>()

    fun getAllJobs(){
        RetrofitApiInstance.getApi().getIndiaJobs(102713980,"any_time").enqueue(object :Callback<IndiaJobsList>{
            override fun onResponse(call: Call<IndiaJobsList>, response: Response<IndiaJobsList>) {
                if(response.body() !=null){
                    indiaJobLiveData.value=response.body()!!.data
                }
            }

            override fun onFailure(call: Call<IndiaJobsList>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun observeIndiaJobs():LiveData<List<Data>>{
        return indiaJobLiveData
    }

}






//fun getIndiaJobList() {
//    RetrofitApiInstance.getApi().getIndiaJobs()
//        .enqueue(object : Callback<Data>{
//            override fun onResponse(call: Call<Data>, response: Response<Data>) {
//                if(response.body() !=null){
//
//                }
//            }
//
//            override fun onFailure(call: Call<Data>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
//}
//
//fun observeIndiaJobs(): LiveData<Data> {
//    return indiaJobLiveData
//}