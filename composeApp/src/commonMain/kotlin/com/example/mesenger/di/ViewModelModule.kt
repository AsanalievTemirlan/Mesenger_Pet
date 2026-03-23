package com.example.mesenger.di

import com.example.mesenger.presenatation.screens.auth.RegistrationViewModel
import org.koin.dsl.module

val viewModelModule = module {

    single { RegistrationViewModel(get()) }

}