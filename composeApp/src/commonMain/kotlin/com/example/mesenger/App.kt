package com.example.mesenger

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import com.example.mesenger.presenatation.navigation.AppNavHost
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

@Composable
@Preview
fun App(deepLinkUrl: String? = null) {
    InitCoil()
    MaterialTheme {
        AppNavHost(deepLinkUrl)
    }
}

@Composable
fun InitCoil() {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .components {
                add(KtorNetworkFetcherFactory(HttpClient {
                    install(ContentNegotiation) {
                        json(Json {
                            ignoreUnknownKeys = true
                        })
                    }
                }))
            }
            .build()
    }
}
