plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.amirmousavi.core"
}
dependencies {

    //Room
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.compiler)

    implementation (libs.gson)
    implementation (libs.squareup.retrofit)
    implementation (libs.squareup.converter.gson)

}