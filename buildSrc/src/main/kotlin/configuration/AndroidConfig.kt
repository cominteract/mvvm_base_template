package configuration

object AndroidConfig {
    val versionName = "7.0.15" // X.Y.Z; X = Major, Y = minor, Z = Patch level
    private val versionCodeBase = 70150 // XYYZZM; M = Module (tv, mobile)
    val versionCodeMobile = versionCodeBase + 3

    const val COMPILE_SDK = 30
    const val TARGET_SDK = 30
    const val MIN_SDK = 23
    const val APPLICATION_ID = "com.ainsigne.mvvmtemplate"
}