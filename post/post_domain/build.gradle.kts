plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.amirmousavi.post_domain"
}
dependencies {

    implementation(project(":core"))
    implementation(libs.androidx.paging.runtime.ktx)

}