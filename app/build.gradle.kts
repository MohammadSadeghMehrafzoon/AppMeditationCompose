plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ir.misterdeveloper.aramkadeapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "ir.misterdeveloper.aramkadeapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-compose:1.6.0")
    implementation (platform("androidx.compose:compose-bom:2024.11.00"))
    implementation("androidx.compose.ui:ui")
    implementation ("androidx.compose.material:material:$1.1.1")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation ("com.android.support.test:runner:1.0.1")
    implementation (platform("androidx.compose:compose-bom:2024.11.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.3")

    // System Ui Controller
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.17.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.4.2")

    // LiveData-State
    implementation("androidx.compose.runtime:runtime-livedata:1.7.5")

    // Coil
    implementation("io.coil-kt:coil-compose:2.4.0")


    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")

    // Koin
    implementation("dev.burnoo:cokoin:1.0.0")
    implementation ("dev.burnoo:cokoin-android-viewmodel:1.0.0")
    implementation ("dev.burnoo:cokoin-android-navigation:1.0.0")// for Androidx ViewModel
}