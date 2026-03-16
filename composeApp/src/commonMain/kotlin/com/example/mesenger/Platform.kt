package com.example.mesenger

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform