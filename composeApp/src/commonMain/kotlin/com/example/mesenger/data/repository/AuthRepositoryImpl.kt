package com.example.mesenger.data.repository

import com.example.mesenger.data.api.ApiEndpoints.SING_UP
import com.example.mesenger.data.dto.toDto
import com.example.mesenger.domain.models.RegistrationModel
import com.example.mesenger.domain.repository.AuthRepository
import com.example.mesenger.uitil.Result
import com.example.mesenger.uitil.map
import com.example.mesenger.uitil.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.Serializable
import util.NetworkError


class AuthRepositoryImpl(
    private val client: HttpClient
) : AuthRepository {

    override suspend fun register(model: RegistrationModel): Result<Unit, NetworkError> {
        return safeCall<AuthResponse> {
            client.post(SING_UP) {
                setBody(model.toDto())
            }
        }.map { Unit }
    }
}

@Serializable
data class AuthResponse(
    val access_token: String? = null,
    val refresh_token: String? = null
)
