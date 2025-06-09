package com.example.freightcalc

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform