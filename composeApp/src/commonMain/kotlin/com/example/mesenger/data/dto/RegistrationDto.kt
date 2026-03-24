package com.example.mesenger.data.dto

import com.example.mesenger.domain.models.RegistrationModel
import kotlinx.serialization.Serializable


@Serializable
data class RegistrationDto(
    val email: String,
    val password: String,
    val data: Map<String, String>? = null
)

fun RegistrationDto.toDomain(): RegistrationModel {
    return RegistrationModel(email, password, data)
}
fun RegistrationModel.toDto(): RegistrationDto {
    return RegistrationDto(email, password, data)
}