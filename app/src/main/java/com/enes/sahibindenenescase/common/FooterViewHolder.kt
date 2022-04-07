package com.enes.sahibindenenescase.common

import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.enes.sahibindenenescase.databinding.ItemPagingFooterBinding
import com.enes.sahibindenenescase.util.executeWithAction


class FooterViewHolder(
    private val binding: ItemPagingFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        binding.executeWithAction {
            footerUiState = FooterUiState(loadState)
        }
    }
}

