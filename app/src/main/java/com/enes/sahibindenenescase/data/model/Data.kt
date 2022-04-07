package com.enes.sahibindenenescase.data.model

data class Data(
    val created_at: String?= "",
    val id: String,
    val source: String,
    val text: String,
    val authorId: String? = "",
    val attachments: Attachments? = null,
    //User Profile
    val profile_image_url: String? = "",
    val description: String? = "",
    val name: String? = "",
    val location: String? = "",
    val username: String? = "",
    val public_metrics: PublicMetrics? = null



)