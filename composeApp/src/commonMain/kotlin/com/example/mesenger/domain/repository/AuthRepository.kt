package com.example.mesenger.domain.repository

import util.NetworkError
import com.example.mesenger.uitil.Result

interface AuthRepository {
    suspend fun register(email: String, password: String): Result<Unit, NetworkError>
}
