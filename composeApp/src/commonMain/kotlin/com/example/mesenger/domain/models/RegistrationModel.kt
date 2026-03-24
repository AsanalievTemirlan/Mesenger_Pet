package com.example.mesenger.domain.models

data class RegistrationModel(
    val email: String,
    val password: String,
    val data: Map<String, String>? = null
)