// Top-level build file where you can add configuration options common to all sub-projects/modules.
apply (plugin = "org.jetbrains.dokka")

buildscript {
    val kotlin_version by extra("1.5.10")
    val hiltVersion = "2.38.1"
    val gradleVersion = Versions.ANDROID_GRADLE_PLUGIN
    val googleServices = "4.3.5"
    val firebaseVersion = "2.8.0"
    val navigationVersion = "2.3.5"
    val spotlessVersion = "5.10.1"
    val serializationVersion = "1.5.0"

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.diffplug.spotless:spotless-plugin-gradle:$spotlessVersion")
        classpath("com.android.tools.build:gradle:$gradleVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$serializationVersion")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.17.1")
        classpath("com.google.gms:google-services:$googleServices")
        classpath("com.google.firebase:firebase-crashlytics-gradle:$firebaseVersion")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.5.0")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}


allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

val installGitHooks by tasks.register("installGitHooks",
    Copy::class.java) {
    from(File(rootProject.rootDir, "pre-commit"))
    into(File(rootProject.rootDir, ".git/hooks"))
    fileMode = 493 // 493 decimal == 755 octal; unix file permissions
}



tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
