package com.example.narutoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.narutoapp.core.di.AppContainer
import com.example.narutoapp.features.naruto.di.NarutoModule
import com.example.narutoapp.features.naruto.presentation.screens.NarutoScreen

class MainActivity : ComponentActivity() {

    lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appContainer = AppContainer(this)
        val narutoModule = NarutoModule(appContainer)

        enableEdgeToEdge()
        setContent {
                NarutoScreen(narutoModule.provideNarutoViewModelFactory())
            }
        }
    }