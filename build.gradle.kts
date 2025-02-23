// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.android.library") version "8.2.0" apply false
}

buildscript {
    dependencies {
        // Kotlin Gradle plugin
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
        classpath("com.android.tools.build:gradle:8.2.0")
        // Hilt Gradle plugin
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
}