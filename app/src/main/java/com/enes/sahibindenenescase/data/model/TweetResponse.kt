package com.enes.sahibindenenescase.data.model

import com.google.gson.annotations.SerializedName

data class TweetResponse(
    @SerializedName("data")
    val data: List<Data>,
    val meta: Meta
):BaseModel()