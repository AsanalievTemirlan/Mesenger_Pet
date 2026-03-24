package com.example.mesenger.presenatation.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mesenger.presenatation.components.BaseTextInput
import com.example.mesenger.presenatation.components.SpaV
import com.example.mesenger.presenatation.ui.GoogleRed
import com.example.mesenger.presenatation.ui.PrimaryColor
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegistrationScreen(navController: NavController) {
    val vm = koinViewModel<RegistrationViewModel>()
    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) {
        vm.effect.collectLatest { effect ->
            when (effect) {
                is RegistrationEffect.NavigateToHome -> {
                    // navController.navigate("home")
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Регистрация",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        SpaV(32)

        BaseTextInput(
            value = state.name,
            onValueChange = { vm.onIntent(RegistrationIntent.NameChanged(it)) },
            label = "Имя",
            leadingIcon = { Icon(Icons.Default.SupervisedUserCircle, contentDescription = null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            state = state,
        )
        BaseTextInput(
            value = state.email,
            onValueChange = { vm.onIntent(RegistrationIntent.EmailChanged(it)) },
            label = "Электронная почта",
            placeholder = "example@gmail.com",
            leadingIcon = { Icon(Icons.Default.Mail, contentDescription = null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            state = state
        )
        BaseTextInput(
            value = state.password,
            onValueChange = { vm.onIntent(RegistrationIntent.PasswordChanged(it)) },
            label = "Пароль",
            leadingIcon = { Icon(Icons.Default.Password, contentDescription = null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            state = state
        )

        SpaV(16)

        state.error?.let { error ->
            Text(
                text = "Ошибка: $error",
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Button(
            onClick = {
                vm.onIntent(RegistrationIntent.RegisterClicked)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
            enabled = !state.isLoading
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            } else {
                Text("Продолжить", fontSize = 16.sp, color = Color.White)
            }
        }

        if (state.isSuccess) {
            Text("Успешно!", color = Color.Green, modifier = Modifier.padding(top = 8.dp))
        }

        SpaV(24)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(modifier = Modifier.weight(1f))
            Text(
                text = " или ",
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Color.Gray,
                fontSize = 14.sp
            )
            HorizontalDivider(modifier = Modifier.weight(1f))
        }

        SpaV(24)

        OutlinedButton(
            onClick = { /* TODO: Google registration logic */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(width = 1.dp),
            enabled = !state.isLoading
        ) {
            Text(
                text = "Войти через Google",
                fontSize = 16.sp,
                color = GoogleRed,
                fontWeight = FontWeight.Medium
            )
        }

        SpaV(16)

        TextButton(onClick = { /* TODO: Navigate to Login */ }) {
            Text("Уже есть аккаунт? Войти", color = PrimaryColor)
        }
    }
}
