package com.example.mesenger.presenatation.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mesenger.domain.models.RegistrationModel
import com.example.mesenger.domain.usecase.RegisterUseCase
import com.example.mesenger.uitil.Result
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegistrationState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<RegistrationEffect>()
    val effect = _effect.asSharedFlow()

    fun onIntent(intent: RegistrationIntent) {
        when (intent) {
            is RegistrationIntent.NameChanged -> {
                _state.update { it.copy(name = intent.name) }
            }
            is RegistrationIntent.EmailChanged -> {
                _state.update { it.copy(email = intent.email) }
            }
            is RegistrationIntent.PasswordChanged -> {
                _state.update { it.copy(password = intent.password) }
            }
            RegistrationIntent.RegisterClicked -> {
                register()
            }
        }
    }

    private fun register() {
        val currentState = _state.value
        viewModelScope.launch {
            Napier.d("Начинаем регистрацию для ${currentState.email}", tag = "REG")
            _state.update { it.copy(isLoading = true, error = null) }

            val result = registerUseCase(
                RegistrationModel(
                    email = currentState.email,
                    password = currentState.password,
                    data = mapOf("name" to currentState.name)
                )
            )

            when (result) {
                is Result.Success -> {
                    Napier.d("Регистрация успешна", tag = "REG")
                    _state.update { it.copy(isLoading = false, isSuccess = true) }
                    _effect.emit(RegistrationEffect.NavigateToHome)
                }

                is Result.Error -> {
                    Napier.e("Ошибка регистрации: ${result.error}", tag = "REG")
                    _state.update { it.copy(isLoading = false, error = result.error) }
                }
            }
        }
    }
}
