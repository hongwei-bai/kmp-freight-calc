package au.com.kmpfreightcalc

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform