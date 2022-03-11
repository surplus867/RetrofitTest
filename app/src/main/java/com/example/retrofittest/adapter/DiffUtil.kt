package com.example.retrofittest.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.retrofittest.model.Post

class DiffUtil(
    private val oldList: List<Post>,
    private val newList: List<Post>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].userId == newList[newItemPosition].userId
                && oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].title== newList[newItemPosition].title
                && oldList[oldItemPosition].body== newList[newItemPosition].body
    }
}