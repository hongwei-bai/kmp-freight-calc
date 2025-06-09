package com.example.freightcalc

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "kmp-freight-calc",
    ) {
        App()
    }
}