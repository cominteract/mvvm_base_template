import Versions.COMPILE_SDK
import Versions.MIN_SDK

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("io.gitlab.arturbosch.detekt")
    id("com.diffplug.spotless")
    id("org.jetbrains.dokka")
}

apply(from = "${rootProject.projectDir}/common.gradle")

dependencies {
    implementation(Libraries.appCompat)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.materialDesign)
    implementation(Libraries.androidLegacySupport)
    implementation(Libraries.supportAnnotation)

    implementation(Libraries.whartonTimber)
    testImplementation(Libraries.junitTestImp)
    testImplementation(Libraries.coroutineTestImp)
    testImplementation(Libraries.archCoreTestImp)
    testImplementation(Libraries.junitRunner)
    testImplementation(Libraries.roboElectric)

    androidTestImplementation(Libraries.runnerAndroidTestImp)
    androidTestImplementation(Libraries.expressoCoreAnroidTestImp)
    androidTestImplementation(Libraries.junitRunner)
}
