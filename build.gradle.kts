plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.com.android.library) apply false
    kotlin("android").version(libs.versions.kotlinVersion) apply false
    kotlin("multiplatform").version(libs.versions.kotlinVersion) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.app.cash.sqldelight) apply false
}
