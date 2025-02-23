plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.ahmedraafat.halanassignment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ahmedraafat.halanassignment"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "TWITTER_API_KEY", "\"${project.findProperty("TWITTER_API_KEY") ?: ""}\"")
        buildConfigField("String", "TWITTER_API_SECRET", "\"${project.findProperty("TWITTER_API_SECRET") ?: ""}\"")
        buildConfigField("String", "ACCESS_TOKEN", "\"${project.findProperty("ACCESS_TOKEN") ?: ""}\"")
        buildConfigField("String", "ACCESS_SECRET", "\"${project.findProperty("ACCESS_SECRET") ?: ""}\"")

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
    kapt {
        correctErrorTypes = true
        useBuildCache = false
        // Enable detailed logging
        javacOptions {
            option("-Xmaxerrs", 1000)
            option("-Xmaxwarns", 1000)
        }
    }

}

dependencies {
    implementation(project(":tweetcounter"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.22")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")


    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("androidx.databinding:databinding-runtime:8.8.1")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.45")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.45")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    testImplementation ("androidx.arch.core:core-testing:2.2.0") // For InstantTaskExecutorRule
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")// For coroutine testing
    testImplementation ("io.mockk:mockk:1.13.4")
}
