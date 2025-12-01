plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.restock"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.restock"
        minSdk = 25
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

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    // ========== CORE ==========
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")

    // ========== COMPOSE ==========
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Compose Activity
    implementation("androidx.activity:activity-compose:1.9.3")

    // ========== DATASTORE ==========
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // Material Icons
    implementation("androidx.compose.material:material-icons-extended")

    // ========== ROOM ==========
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // ========== CAMERA ==========
    implementation("androidx.camera:camera-core:1.2.0-beta02")
    implementation("androidx.camera:camera-camera2:1.2.0-beta02")
    implementation("androidx.camera:camera-lifecycle:1.2.0-beta02")
    implementation("androidx.camera:camera-view:1.2.0-beta02")

    // ========== QR SCAN ==========
    implementation("com.google.zxing:core:3.3.3")

    // ========== SYSTEM UI CONTROLLER ==========
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.24.10-beta")

    // ========== RETROFIT + GSON (API CLIMA) ==========
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // ========== TESTS ==========
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
}
