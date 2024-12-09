plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.amirmousavi.post_data"
}
dependencies {

    implementation(libs.gson)
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.converter.gson)

    implementation(libs.androidx.paging.runtime.ktx)


    implementation(project(":core"))
    implementation(project(":post:post_domain"))

}