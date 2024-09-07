package com.cesar2m.guessinggame

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cesar2m.guessinggame.databinding.TopicItemBinding


class TopicItemAdapter(val clickListener: (topicId: Long) -> Unit): ListAdapter<Topic, TopicItemAdapter.TopicItemViewHolder>(ChooseTopicDiffItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TopicItemViewHolder = TopicItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: TopicItemViewHolder, position: Int){
        val item = getItem(position)
        holder.bind(item, clickListener)
    }
    class TopicItemViewHolder(val binding: TopicItemBinding):RecyclerView.ViewHolder (binding.root){
        companion object{
            fun inflateFrom(parent: ViewGroup): TopicItemViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TopicItemBinding.inflate(layoutInflater, parent, false)

                return TopicItemViewHolder(binding)
            }
        }

        fun bind(item: Topic, clickListener: (topicId: Long) -> Unit ){
            binding.topic = item
            binding.topicCheck.setOnClickListener{clickListener(item.topicId)}
        }
    }

}

