package com.example.mesenger.presenatation.screens.auth

sealed interface RegistrationIntent {
    data class NameChanged(val name: String) : RegistrationIntent
    data class EmailChanged(val email: String) : RegistrationIntent
    data class PasswordChanged(val password: String) : RegistrationIntent
    object RegisterClicked : RegistrationIntent
}