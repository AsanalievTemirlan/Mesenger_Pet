package com.example.mesenger.di

import com.example.mesenger.domain.usecase.RegisterUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { RegisterUseCase(get()) }

}