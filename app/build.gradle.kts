plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
    implementation("androidx.compose.material:material-icons-extended")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation ("androidx.compose.ui:ui:1.2.1")
    implementation ("androidx.compose.material:material:1.2.1")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.2.1")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.2.1")

    implementation ("androidx.core:core-ktx:1.9.0")
    implementation ("androidx.activity:activity-compose:1.6.0")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.24.10-beta")

    implementation ("androidx.camera:camera-core:1.2.0-beta02")
    implementation ("androidx.camera:camera-camera2:1.2.0-beta02")
    implementation ("androidx.camera:camera-lifecycle:1.2.0-beta02")
    implementation ("androidx.camera:camera-view:1.2.0-beta02")

    implementation ("com.google.zxing:core:3.3.3")
}