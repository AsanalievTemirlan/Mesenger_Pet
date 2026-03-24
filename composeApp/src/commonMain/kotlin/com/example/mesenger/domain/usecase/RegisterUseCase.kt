package com.example.mesenger.domain.usecase

import com.example.mesenger.domain.models.RegistrationModel
import com.example.mesenger.domain.repository.AuthRepository
import util.NetworkError
import com.example.mesenger.uitil.Result

class RegisterUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(model: RegistrationModel): Result<Unit, NetworkError> =
        authRepository.register(model)
}