plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.carepoint"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.carepoint"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    // Android UI
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Retrofit bundle
    implementation(libs.bundles.retrofit.bundle)

    // Image Loading - Coil
    implementation(libs.coil)

    // Room bundle
    implementation(libs.bundles.room.bundle)
    annotationProcessor(libs.room.compiler) // required for Java

    // DialogPlus
    implementation(libs.dialogplus)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
