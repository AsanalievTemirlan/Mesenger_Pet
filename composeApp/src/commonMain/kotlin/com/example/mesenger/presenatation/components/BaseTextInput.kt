package com.example.mesenger.presenatation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mesenger.presenatation.screens.auth.RegistrationState
import com.example.mesenger.presenatation.screens.auth.RegistrationViewModel

@Composable
fun BaseTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    state: RegistrationState,
    label: String,
    placeholder: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions,
        shape = RoundedCornerShape(12.dp),
        enabled = !state.isLoading,
        placeholder = {
            if (placeholder != null) {
                Text(text = placeholder)
            }
        },
    )
}
