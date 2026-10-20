package com.example.tu_monedita

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform