// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("com.android.application") version "7.4.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
}
