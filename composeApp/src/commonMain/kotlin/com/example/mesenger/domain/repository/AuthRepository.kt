package com.example.mesenger.domain.repository

import com.example.mesenger.domain.models.RegistrationModel
import util.NetworkError
import com.example.mesenger.uitil.Result

interface AuthRepository {
    suspend fun register(model: RegistrationModel): Result<Unit, NetworkError>
}
