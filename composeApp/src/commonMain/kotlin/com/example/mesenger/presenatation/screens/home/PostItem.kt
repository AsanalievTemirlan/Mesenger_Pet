package com.example.mesenger.presenatation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.mesenger.domain.models.PostModel
import com.example.mesenger.presenatation.components.Spa
import com.example.mesenger.presenatation.components.SpaH
import com.example.mesenger.uitil.formatPostTime
import mesenger.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun PostItem(post: PostModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = post.userImage,
                contentDescription = "User Image",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            SpaH(12)

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = post.userName,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.sp
                            )
                        )
                        SpaH(8)
                        Text(
                            text = formatPostTime(post.timestamp),
                            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                        )
                    }


                }

                Spa(2)

                Text(
                    text = post.postText,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 20.sp
                )

                if (post.postImage != null) {
                    Spa(10)
                    AsyncImage(
                        model = post.postImage,
                        contentDescription = "Post Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.FillWidth
                    )
                }

                Spa(12)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ActionIcon(
                        icon = if (post.isLiked) Res.drawable.ic_fill_heart else Res.drawable.ic_heart,
                        count = post.likeCount,
                        tint = if (post.isLiked) Color.Red else LocalContentColor.current
                    )
                    ActionIcon(Res.drawable.ic_comment, post.commentCount)
                    ActionIcon(Res.drawable.ic_repost, post.repostsCount)
                    ActionIcon(Res.drawable.ic_send, post.shareCount)
                }
            }
        }

        Spa(12)
    }
}

@Composable
fun ActionIcon(
    icon: DrawableResource,
    count: Int,
    tint: Color = LocalContentColor.current
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(22.dp),
            tint = tint
        )
        if (count > 0) {
            SpaH(6)
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
            )
        }
    }
}