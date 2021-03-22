package com.syftapp.codetest.posts

import androidx.recyclerview.widget.DiffUtil
import com.syftapp.codetest.data.model.domain.Post

/*
    DiffUtilCallback is used to calculate the list objects data difference
 */

internal class DiffUtilCallback (var newList: List<Post>, var oldList: List<Post>): DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }
}