package com.example.mesenger.presenatation.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mesenger.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import util.NetworkError
import com.example.mesenger.uitil.Result
import io.github.aakira.napier.Napier

class RegistrationViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<RegistrationState>(RegistrationState.Idle)
    val state = _state.asStateFlow()

    fun register(email: String, password: String) {
        viewModelScope.launch {
            Napier.d("Начинаем регистрацию для $email", tag = "REG")
            _state.value = RegistrationState.Loading

            val result = registerUseCase(email, password)

            when (result) {
                is Result.Success -> {
                    Napier.d("Регистрация успешна", tag = "REG")
                    _state.value = RegistrationState.Success
                }

                is Result.Error -> {
                    Napier.e("Ошибка регистрации: ${result.error}", tag = "REG")
                    _state.value = RegistrationState.Error(result.error)
                }
            }
        }
    }

    sealed interface RegistrationState {
        object Idle : RegistrationState
        object Loading : RegistrationState
        object Success : RegistrationState
        data class Error(val error: NetworkError) : RegistrationState
    }
}
