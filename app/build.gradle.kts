import Versions.APPLICATION_ID
import Versions.COMPILE_SDK
import Versions.MIN_SDK

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("io.gitlab.arturbosch.detekt")
    id("com.diffplug.spotless")
    id("com.google.firebase.crashlytics")
    id("org.jetbrains.dokka")
}

apply(from = "../tools/ktlint.gradle.kts")

android {
    compileSdkVersion(COMPILE_SDK)

    defaultConfig {
        applicationId = APPLICATION_ID
        minSdkVersion(MIN_SDK)
        targetSdkVersion(COMPILE_SDK)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.ainsigne.mvvmtemplate.HiltRunnerTest"
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    testOptions.unitTests.isIncludeAndroidResources = true
}

detekt {
    // Builds the AST in parallel. Rules are always executed in parallel. Can lead to speedups in larger projects. `false` by default.
    config = files("../quality/detekt_app_config.yml")

    // Define the detekt configuration(s) you want to use. Defaults to the default detekt configuration.
    buildUponDefaultConfig = false

    input =
        files( // The directories where detekt looks for source files. Defaults to `files("src/main/java", "src/main/kotlin")`.
            "src/main/kotlin",
            "src/main/java"
        )
    parallel = false

    // Interpret config files as updates to the default config. `false` by default.
    // baseline = file("path/to/baseline.xml")
    // Specifying a baseline file. All findings stored in this file in subsequent runs of detekt.
    // disableDefaultRuleSets = false
    // Disables all default detekt rulesets and will only run detekt with custom rules defined in plugins passed in with `detektPlugins` configuration. `false` by default.
    debug = false
    // Adds debug output during task execution. `false` by default.
    ignoreFailures = false
    // If set to `true` the build does not fail when the maxIssues count was reached. Defaults to `false`.
    reports {
        xml {
            enabled = true
            // Enable/Disable XML report (default: true)
            destination = file("build/reports/detekt.xml")
            // Path where XML report will be stored (default: `build/reports/detekt/detekt.xml`)
        }
        html {
            enabled = true
            // Enable/Disable HTML report (default: true)
            destination = file("build/reports/detekt.html")
            // Path where HTML report will be stored (default: `build/reports/detekt/detekt.html`)
        }
    }
}

spotless {
    kotlin {
        target("**/*.kt")
        ktlint("0.42.1")
    }
    kotlinGradle {
        target("*.gradle.kts", "additionalScripts/*.gradle.kts")
        ktlint("0.42.1")
    }
}

dependencies {
    implementation(project(":ui"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.appCompat)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.materialDesign)
    implementation(Libraries.androidLegacySupport)
    implementation(Libraries.supportAnnotation)
    implementation(Libraries.fragmentKtx)
    implementation(Libraries.fragment)
    implementation(Libraries.activity)
    implementation(Libraries.airbnb)
    implementation(project(":data"))
    implementation(project(":core"))
    implementation(project(":common"))
    implementation(project(":features:login"))
    implementation(project(":features:chat"))
    implementation(project(":features:futurama"))
    implementation(project(":features:profile"))
    implementation(project(":features:home"))
    implementation(Libraries.appInspectorRuntime)

    implementation(Libraries.navFragment)
    implementation(Libraries.navUi)

    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitGson)
    implementation(Libraries.pagingLibrary)

    implementation(Libraries.roomKtx)
    implementation(Libraries.roomRuntime)
    implementation(Libraries.roomPaging)
    kapt(Libraries.kaptRoom)

    implementation(Libraries.coreKtx)
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.coroutinesKotlinx)
    implementation(Libraries.liveData)
    implementation(Libraries.appInspectorRuntime)

    implementation(Libraries.whartonTimber)
    implementation(Libraries.httpLogging)
    implementation(Libraries.viewModelKtx)

    kapt(Libraries.kaptAndroidXCompiler)
    implementation(Libraries.lifecycleRuntime)

    implementation(Libraries.imageZoomLib)

    implementation(Libraries.fireBaseMessaging)
    implementation(Libraries.firebaseAuth)

    implementation(Libraries.facebook)

    implementation(platform(Libraries.firebaseBom))
    implementation(Libraries.firebaseCrashlytics)
    implementation(Libraries.firebaseAnalytics)

    implementation(Libraries.markwonCore)
    implementation(Libraries.markwonPicasso)
    implementation(Libraries.markwonStrikeThrough)
    implementation(Libraries.markwonTables)
    implementation(Libraries.markwonTaskList)
    implementation(Libraries.markwonLinkify)

    implementation(Libraries.phoneNumberKit)
    implementation(Libraries.easyPermission)
    testImplementation(Libraries.junitTestImp)
    testImplementation(Libraries.coroutineTestImp)
    testImplementation(Libraries.archCoreTestImp)
    testImplementation(Libraries.junitRunner)
    testImplementation(Libraries.roboElectric)
    testImplementation(Libraries.coroutineTest)
    testImplementation(Libraries.mockitoTest)
    testImplementation(Libraries.kotlinTest)
    implementation(Libraries.pusherClient)
    androidTestImplementation(Libraries.coroutineTest)
    androidTestImplementation(Libraries.runnerAndroidTestImp)
    androidTestImplementation(Libraries.expressoCoreAnroidTestImp)
    androidTestImplementation(Libraries.junitRunner)
}
