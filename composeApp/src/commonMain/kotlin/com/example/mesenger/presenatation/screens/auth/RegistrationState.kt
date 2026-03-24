package com.example.mesenger.presenatation.screens.auth

import util.NetworkError

data class RegistrationState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: NetworkError? = null,
    val isSuccess: Boolean = false
)