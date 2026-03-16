package com.example.mesenger.domain.models

data class PostModel(
    val userName: String,
    val userImage: String,
    val postImage: String?,
    val likeCount: Int,
    val commentCount: Int,
    val shareCount: Int,
    val isLiked: Boolean,
    val timestamp: Long,
    val postText: String
)