package com.cesar2m.guessinggame

import androidx.recyclerview.widget.DiffUtil

class ChooseTopicDiffItemCallBack: DiffUtil.ItemCallback<Topic>() {

    override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean = (
        oldItem.topicId == newItem.topicId
    )

    override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean = (oldItem == newItem)

}