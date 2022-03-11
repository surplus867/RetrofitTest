package com.example.retrofittest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittest.databinding.RowLayoutBinding
import com.example.retrofittest.model.Post


class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var myList = emptyList<Post>()

    class MyViewHolder(private val binding: RowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.post = post
            //binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentPost = myList[position]
        holder.bind(currentPost)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun setData(post: List<Post>) {
        val diffUtil = DiffUtil(myList, post)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        this.myList = post
        diffUtilResult.dispatchUpdatesTo(this)
    }
}