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
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mesenger.presenatation.components.SpaV
import com.example.mesenger.presenatation.ui.GoogleRed
import com.example.mesenger.presenatation.ui.PrimaryColor
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegistrationScreen(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }
    val vm = koinViewModel<RegistrationViewModel>()
    val state by vm.state.collectAsState()

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

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Имя") },
            leadingIcon = { Icon(Icons.Default.VerifiedUser, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            shape = RoundedCornerShape(12.dp),
            enabled = state !is RegistrationViewModel.RegistrationState.Loading
        )
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Электронная почта") },
            placeholder = { Text("example@gmail.com") },
            leadingIcon = { Icon(Icons.Default.Mail, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            shape = RoundedCornerShape(12.dp),
            enabled = state !is RegistrationViewModel.RegistrationState.Loading
        )
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Пароль") },
            leadingIcon = { Icon(Icons.Default.Password, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            shape = RoundedCornerShape(12.dp),
            enabled = state !is RegistrationViewModel.RegistrationState.Loading
        )

        SpaV(16)

        if (state is RegistrationViewModel.RegistrationState.Error) {
            Text(
                text = "Ошибка: ${(state as RegistrationViewModel.RegistrationState.Error).error}",
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
            enabled = state !is RegistrationViewModel.RegistrationState.Loading
        ) {
            if (state is RegistrationViewModel.RegistrationState.Loading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            } else {
                Text("Продолжить", fontSize = 16.sp, color = Color.White)
            }
        }

        if (state is RegistrationViewModel.RegistrationState.Success) {
            LaunchedEffect(Unit) {
                // navController.navigate("home") // Например
            }
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
            enabled = state !is RegistrationViewModel.RegistrationState.Loading
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
