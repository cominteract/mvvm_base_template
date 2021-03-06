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
    implementation(Libraries.dagger)
    kapt(Libraries.daggerCompiler)
    kapt(Libraries.daggerProcessor)
    implementation(Libraries.mockitoTest)
    implementation(Libraries.kotlinTest)
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.coroutinesKotlinx)
    testImplementation(Libraries.junitTestImp)
    testImplementation(Libraries.coroutineTestImp)
    testImplementation(Libraries.archCoreTestImp)
    testImplementation(Libraries.junitRunner)
    implementation(Libraries.whartonTimber)
    implementation(Libraries.httpLogging)
    implementation(Libraries.roomKtx)
    implementation(Libraries.roomRuntime)
    implementation(Libraries.roomPaging)
    kapt(Libraries.kaptRoom)
    implementation(Libraries.dataStorePreference)
    implementation(Libraries.kotlinSerializationProperties)
    implementation(Libraries.kotlinSerializationJson)
    implementation(Libraries.okHttp)
    implementation(Libraries.retrofit)
    implementation(Libraries.kotlinSerializationConverter)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
}