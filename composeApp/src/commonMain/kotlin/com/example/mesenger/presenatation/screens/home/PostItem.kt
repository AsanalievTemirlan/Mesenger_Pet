package com.example.mesenger.presenatation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil3.compose.AsyncImage
import com.example.mesenger.domain.models.PostModel
import com.example.mesenger.presenatation.components.Spa
import com.example.mesenger.presenatation.components.SpaH
import com.example.mesenger.uitil.formatPostTime
import mesenger.composeapp.generated.resources.Res
import mesenger.composeapp.generated.resources.ic_comment
import mesenger.composeapp.generated.resources.ic_fill_heart
import mesenger.composeapp.generated.resources.ic_heart
import mesenger.composeapp.generated.resources.ic_repost
import mesenger.composeapp.generated.resources.ic_send
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun PostItem(post: PostModel) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = post.userImage,
                contentDescription = "User Image",
                modifier = Modifier
            )
            SpaH(8)
            Column(

            ) {
                Row {
                    Text(post.userName)
                    Text(formatPostTime(post.timestamp))
                }
                Text(post.postText)
                Spa(4)
                AsyncImage(
                    model = post.postImage,
                    contentDescription = "Post Image"
                )
                Spa(4)
                Row {
                    Icon(
                        painter = if (post.isLiked) painterResource(Res.drawable.ic_fill_heart)
                        else painterResource(Res.drawable.ic_heart),
                        contentDescription = "Like",
                        tint = if (post.isLiked) Color.Red else Color.Unspecified
                    )
                    SpaH()
                    PostIcons(post.commentCount, Res.drawable.ic_comment)
                    PostIcons(post.repostsCount, Res.drawable.ic_repost)
                    PostIcons(post.shareCount, Res.drawable.ic_send)
                }
            }

        }
    }

}

@Composable
fun PostIcons(count: Int, icon: DrawableResource){
    Icon(
        painter = painterResource(icon),
        contentDescription = "Like",
        tint = Color.Unspecified
    )
    Text(
        text = count.toString()
    )
    SpaH()
}