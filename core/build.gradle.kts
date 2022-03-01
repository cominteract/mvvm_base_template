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
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":data"))
    implementation(Libraries.whartonTimber)
    implementation(Libraries.appCompat)
    implementation(Libraries.dagger)
    kapt(Libraries.daggerCompiler)
    kapt(Libraries.daggerProcessor)
    implementation(Libraries.roomKtx)
    implementation(Libraries.roomRuntime)
    implementation(Libraries.roomPaging)
    kapt(Libraries.kaptRoom)
    implementation(Libraries.okHttp)
    implementation(Libraries.httpLogging)
    implementation(Libraries.retrofit)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    testImplementation(Libraries.junitTestImp)
    testImplementation(Libraries.coroutineTestImp)
    testImplementation(Libraries.archCoreTestImp)
    testImplementation(Libraries.junitRunner)
    testImplementation(Libraries.roboElectric)

    androidTestImplementation(Libraries.runnerAndroidTestImp)
    androidTestImplementation(Libraries.expressoCoreAnroidTestImp)
    androidTestImplementation(Libraries.junitRunner)
}
