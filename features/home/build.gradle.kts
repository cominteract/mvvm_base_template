

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("io.gitlab.arturbosch.detekt")
    id("com.diffplug.spotless")
    id("org.jetbrains.dokka")
}

apply(from = "${rootProject.projectDir}/common.gradle")

android {
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":ui"))
    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(Libraries.whartonTimber)
    implementation(Libraries.appCompat)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.materialDesign)
    implementation(Libraries.androidLegacySupport)
    implementation(Libraries.supportAnnotation)
    implementation(Libraries.fragmentKtx)
    implementation(Libraries.dagger)
    kapt(Libraries.daggerCompiler)
    kapt(Libraries.daggerProcessor)
    testImplementation(Libraries.junitTestImp)
    testImplementation(Libraries.coroutineTestImp)
    testImplementation(Libraries.archCoreTestImp)
    testImplementation(Libraries.junitRunner)
    testImplementation(Libraries.roboElectric)
    implementation(Libraries.mockitoTest)
    implementation(Libraries.kotlinTest)
    implementation(Libraries.httpLogging)
    implementation(Libraries.kotlinSerializationProperties)
    implementation(Libraries.kotlinSerializationJson)
    implementation(Libraries.okHttp)
    implementation(Libraries.retrofit)
    implementation(Libraries.kotlinSerializationConverter)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.coroutinesKotlinx)
    implementation(Libraries.viewModelKtx)
    implementation(Libraries.lifecycleRuntime)
    implementation(Libraries.coreKtx)
    implementation(Libraries.liveData)
    androidTestImplementation(Libraries.runnerAndroidTestImp)
    androidTestImplementation(Libraries.expressoCoreAnroidTestImp)
    androidTestImplementation(Libraries.junitRunner)
}
