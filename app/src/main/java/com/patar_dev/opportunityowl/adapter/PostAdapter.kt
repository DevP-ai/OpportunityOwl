package com.patar_dev.opportunityowl.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.patar_dev.opportunityowl.databinding.PostDesignBinding
import com.patar_dev.opportunityowl.model.postmodel.PostModel

class PostAdapter(private val postList: List<PostModel>):RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(var binding:PostDesignBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            PostDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(postList[position].postImage)
            .into(holder.binding.userPostImage)

        Glide.with(holder.itemView)
            .load(postList[position].userProfile)
            .into(holder.binding.postUserProfile)

        holder.binding.userPostContent.text=postList[position].content
        holder.binding.postUserName.text=postList[position].userName
        holder.binding.postUserProfession.text=postList[position].userProfession
    }
}