package com.example.mesenger.data

import com.example.mesenger.domain.repository.AuthRepository
import com.example.mesenger.uitil.Result
import com.example.mesenger.uitil.map
import com.example.mesenger.uitil.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.Serializable
import util.NetworkError

class AuthRepositoryImpl(
    private val client: HttpClient
) : AuthRepository {

    private val baseUrl = "https://zcnqrplozjiawkexzxnu.supabase.co"
    private val apiKey = "sb_publishable_0pX_lJTNvvGTwr_nM-nTmQ_ijd6Kt-3"

    override suspend fun register(email: String, password: String): Result<Unit, NetworkError> {
        return safeCall<AuthResponse> {
            client.post("$baseUrl/auth/v1/signup") {
                headers {
                    append("apikey", apiKey)
                    append("Content-Type", "application/json")
                }
                setBody(AuthRequest(email, password))
            }
        }.map { Unit }
    }
}

@Serializable
data class AuthRequest(
    val email: String,
    val password: String
)

@Serializable
data class AuthResponse(
    val access_token: String? = null,
    val refresh_token: String? = null
)
