plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}


android {
    namespace = "com.amirmousavi.core"
    compileSdk = libs.versions.projectCompileSdkVersion.get().toInt()

    defaultConfig {
        minSdk = libs.versions.projectMinSdkVersion.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {


    implementation(libs.gson)
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.converter.gson)
    implementation(libs.androidx.paging.runtime.ktx)


    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    ksp(libs.hilt.ext.compiler)

    coreLibraryDesugaring (libs.desugar.jdk)


    //Room
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

   implementation(libs.androidx.datastore.preferences)

}