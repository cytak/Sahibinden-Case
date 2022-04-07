package com.enes.sahibindenenescase.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.enes.sahibindenenescase.R
import com.enes.sahibindenenescase.databinding.ItemTweetBinding
import com.enes.sahibindenenescase.util.executeWithAction
import com.enes.sahibindenenescase.view.TweetItemUiState
import javax.inject.Inject

class TweetAdapter @Inject constructor() : PagingDataAdapter<TweetItemUiState, TweetAdapter.TweetViewHolder>(
    Comparator) {

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        getItem(position)?.let { tweetItemUiState -> holder.bind(tweetItemUiState) }
        holder.itemView.setOnClickListener {
            setTweetClickListener?.let {
                getItem(position)?.getId()?.let { it1 -> it(it1) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val binding = inflate<ItemTweetBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_tweet,
            parent,
            false
        )

        return TweetViewHolder(binding)
    }

    object Comparator : DiffUtil.ItemCallback<TweetItemUiState>() {
        override fun areItemsTheSame(oldItem: TweetItemUiState, newItem: TweetItemUiState): Boolean {
            return oldItem.getText() == newItem.getText()
        }

        override fun areContentsTheSame(
            oldItem: TweetItemUiState,
            newItem: TweetItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }

    private var setTweetClickListener: ((id: String)->Unit)? = null

    fun tweetClicked(listener:(String) -> Unit){
        setTweetClickListener = listener
    }

  inner  class TweetViewHolder(private val binding: ItemTweetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tweetItemUiState: TweetItemUiState) {
            binding.executeWithAction {
                this.tweetItemUiState = tweetItemUiState
            }
        }
    }

}