package com.example.mesenger.presenatation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mesenger.domain.models.PostModel
import kotlin.time.Clock

@Composable
fun HomeScreen(navController: NavController) {


    val now = Clock.System.now().toEpochMilliseconds()

    val posts = listOf(
        PostModel(
            userName = "Alex",
            userImage = "https://i.pravatar.cc/150?img=1",
            postImage = "https://picsum.photos/400/300?1",
            likeCount = 120,
            commentCount = 14,
            shareCount = 5,
            repostsCount = 8,
            isLiked = false,
            timestamp = now - 1000L * 60 * 5,
            postText = "Первый пост в моей новой соцсети 🚀"
        ),PostModel(
            userName = "Alex",
            userImage = "https://i.pravatar.cc/150?img=1",
            postImage = "https://picsum.photos/400/300?1",
            likeCount = 120,
            commentCount = 14,
            shareCount = 5,
            repostsCount = 8,
            isLiked = false,
            timestamp = now - 1000L * 60 * 5,
            postText = "Первый пост в моей новой соцсети 🚀"
        ),PostModel(
            userName = "Alex",
            userImage = "https://i.pravatar.cc/150?img=1",
            postImage = "https://picsum.photos/400/300?1",
            likeCount = 120,
            commentCount = 14,
            shareCount = 5,
            repostsCount = 8,
            isLiked = false,
            timestamp = now - 1000L * 60 * 5,
            postText = "Первый пост в моей новой соцсети 🚀"
        ),PostModel(
            userName = "Alex",
            userImage = "https://i.pravatar.cc/150?img=1",
            postImage = "https://picsum.photos/400/300?1",
            likeCount = 120,
            commentCount = 14,
            shareCount = 5,
            repostsCount = 8,
            isLiked = false,
            timestamp = now - 1000L * 60 * 5,
            postText = "Первый пост в моей новой соцсети 🚀"
        ),PostModel(
            userName = "Alex",
            userImage = "https://i.pravatar.cc/150?img=1",
            postImage = "https://picsum.photos/400/300?1",
            likeCount = 120,
            commentCount = 14,
            shareCount = 5,
            repostsCount = 8,
            isLiked = false,
            timestamp = now - 1000L * 60 * 5,
            postText = "Первый пост в моей новой соцсети 🚀"
        ),
        PostModel(
            userName = "Diana",
            userImage = "https://i.pravatar.cc/150?img=2",
            postImage = null,
            likeCount = 45,
            commentCount = 6,
            shareCount = 2,
            repostsCount = 1,
            isLiked = true,
            timestamp = now - 1000L * 60 * 15,
            postText = "Сегодня отличный день для кодинга 💻"
        ),
        PostModel(
            userName = "Max",
            userImage = "https://i.pravatar.cc/150?img=3",
            postImage = "https://picsum.photos/400/300?2",
            likeCount = 300,
            commentCount = 50,
            shareCount = 20,
            repostsCount = 15,
            isLiked = true,
            timestamp = now - 1000L * 60 * 60,
            postText = "Посмотрите на этот вид 😍"
        ),
        PostModel(
            userName = "Sasha",
            userImage = "https://i.pravatar.cc/150?img=4",
            postImage = null,
            likeCount = 10,
            commentCount = 2,
            shareCount = 0,
            repostsCount = 0,
            isLiked = false,
            timestamp = now - 1000L * 60 * 120,
            postText = "Учусь KMP, пока сложно 😅"
        ),
        PostModel(
            userName = "Timur",
            userImage = "https://i.pravatar.cc/150?img=5",
            postImage = "https://picsum.photos/400/300?3",
            likeCount = 999,
            commentCount = 120,
            shareCount = 60,
            repostsCount = 80,
            isLiked = true,
            timestamp = now - 1000L * 60 * 180,
            postText = "Это лучший пост в моей жизни 🔥"
        ),
        PostModel(
            userName = "Anna",
            userImage = "https://i.pravatar.cc/150?img=6",
            postImage = null,
            likeCount = 67,
            commentCount = 9,
            shareCount = 3,
            repostsCount = 2,
            isLiked = false,
            timestamp = now - 1000L * 60 * 240,
            postText = "Кто смотрел новый сериал?"
        ),
        PostModel(
            userName = "John",
            userImage = "https://i.pravatar.cc/150?img=7",
            postImage = "https://picsum.photos/400/300?4",
            likeCount = 210,
            commentCount = 34,
            shareCount = 10,
            repostsCount = 12,
            isLiked = false,
            timestamp = now - 1000L * 60 * 300,
            postText = "Путешествие продолжается 🌍"
        ),
        PostModel(
            userName = "Elena",
            userImage = "https://i.pravatar.cc/150?img=8",
            postImage = null,
            likeCount = 5,
            commentCount = 1,
            shareCount = 0,
            repostsCount = 0,
            isLiked = false,
            timestamp = now - 1000L * 60 * 360,
            postText = "Нужно больше кофе ☕"
        ),
        PostModel(
            userName = "Artem",
            userImage = "https://i.pravatar.cc/150?img=9",
            postImage = "https://picsum.photos/400/300?5",
            likeCount = 430,
            commentCount = 70,
            shareCount = 25,
            repostsCount = 30,
            isLiked = true,
            timestamp = now - 1000L * 60 * 420,
            postText = "Новый проект почти готов 💪"
        ),
        PostModel(
            userName = "Kate",
            userImage = "https://i.pravatar.cc/150?img=10",
            postImage = null,
            likeCount = 88,
            commentCount = 12,
            shareCount = 4,
            repostsCount = 6,
            isLiked = true,
            timestamp = now - 1000L * 60 * 480,
            postText = "Люблю такие спокойные вечера ✨"
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn{
                items(posts){ post ->
                    PostItem(post)
                }
            }
        }
    }
}