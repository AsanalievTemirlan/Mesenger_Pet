package com.example.mesenger.uitil

import kotlin.time.Clock
import kotlin.time.Instant

fun formatPostTime(timestamp: Long): String {
    val now = Clock.System.now()
    val postTime = Instant.fromEpochMilliseconds(timestamp)

    val diffSeconds = (now - postTime).inWholeSeconds
    val minutes = diffSeconds / 60
    val hours = minutes / 60
    val days = hours / 24

    return when {
        minutes < 60 -> "${minutes}м"
        hours < 24 -> "${hours}ч"
        else -> "${days}д"
    }
}