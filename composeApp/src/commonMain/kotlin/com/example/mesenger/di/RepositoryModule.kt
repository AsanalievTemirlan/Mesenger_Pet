package com.example.mesenger.di

import com.example.mesenger.data.repository.AuthRepositoryImpl
import com.example.mesenger.domain.repository.AuthRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {

    single { AuthRepositoryImpl(get()) } bind AuthRepository::class

}

