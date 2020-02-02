package com.example.coolimages.di

import com.example.coolimages.MainActivity
import org.koin.dsl.module

val ActivityModule = module {
    factory { MainActivity() }
}