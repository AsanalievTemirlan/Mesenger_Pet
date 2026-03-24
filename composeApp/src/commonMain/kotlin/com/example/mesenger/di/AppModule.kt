package com.example.mesenger.di

import com.example.mesenger.data.repository.AuthRepositoryImpl
import com.example.mesenger.domain.repository.AuthRepository
import com.example.mesenger.domain.usecase.RegisterUseCase
import com.example.mesenger.presenatation.screens.auth.RegistrationViewModel
import io.github.aakira.napier.Napier.d
import io.ktor.client.*
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.bind
import org.koin.dsl.module

const val API_KEY = "sb_publishable_0pX_lJTNvvGTwr_nM-nTmQ_ijd6Kt-3"


val appModule = module {
    single {
        HttpClient {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        d( message, tag = "KTOR")
                    }
                }
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }
            install(DefaultRequest) {
                header("apikey", API_KEY)
                header("Authorization", "Bearer $API_KEY")

                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }

    single { AuthRepositoryImpl(get()) } bind AuthRepository::class
    single { RegisterUseCase(get()) }
    factory { RegistrationViewModel(get()) }
}
