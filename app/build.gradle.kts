plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
    // Kotlin serialization plugin for type safe routes and navigation arguments
    kotlin("plugin.serialization") version "2.0.21"
    id("androidx.navigation.safeargs")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.android.protobuf)
}

android {
    namespace = "guru.mcqai.www"
    compileSdk = 36

    defaultConfig {
        applicationId = "guru.mcqai.www"
        minSdk = 26
        targetSdk = 36
        versionCode = 5
        versionName = "5.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    protobuf {
        protoc {
            artifact = "com.google.protobuf:protoc:4.32.1"
        }
        generateProtoTasks {
            all().forEach { task ->
                task.builtins {
                    create("java"){
                        option("lite")
                    }
                    create("kotlin")
                }
            }
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }

    // Global Java toolchain setting
    kotlin {
        jvmToolchain(17)
    }
}

dependencies {

    //Proto DataStore


    // Preferences DataStore (SharedPreferences like APIs)

    implementation(libs.androidx.datastore)
    implementation(libs.androidx.proto)


    implementation(libs.protobuf.kotlin.lite)

    implementation("com.github.bumptech.glide:glide:5.0.5")

    implementation("com.google.dagger:hilt-android:2.57.1")
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    ksp("com.google.dagger:hilt-android-compiler:2.57.1")


    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.dynamic)


    // Testing Navigation
    androidTestImplementation(libs.androidx.navigation.testing)


    // JSON serialization library, works with the Kotlin serialization plugin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")


    implementation(platform("com.google.firebase:firebase-bom:34.8.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-ai")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}