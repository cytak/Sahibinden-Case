package com.enes.sahibindenenescase.data.model

data class Meta(
    val newest_id: String,
    val next_token: String,
    val oldest_id: String,
    val result_count: Int,
    val previous_token: String? = ""
)