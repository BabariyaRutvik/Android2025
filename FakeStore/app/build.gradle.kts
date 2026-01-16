plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.fakestore"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.fakestore"
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
    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    // room runtime + compiler
    implementation(libs.room.runtime)
    // Java annotation processor
    annotationProcessor(libs.room.compiler)
    // retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    // glide
    implementation(libs.glide)
    annotationProcessor(libs.glideCompiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}