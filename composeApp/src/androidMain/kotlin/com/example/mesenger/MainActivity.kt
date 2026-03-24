package com.example.mesenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    private var deepLinkUrl by mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        deepLinkUrl = intent?.data?.toString()

        setContent {
            App(deepLinkUrl = deepLinkUrl)
        }
    }

    override fun onNewIntent(intent: android.content.Intent) {
        super.onNewIntent(intent)
        deepLinkUrl = intent.data?.toString()
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
