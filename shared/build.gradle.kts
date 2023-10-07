import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("app.cash.sqldelight") version "2.0.0"
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }


    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }


    sourceSets {
        targetHierarchy.default()
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.sqldelight.coroutines.extensions)
                implementation(libs.koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))

            //    ksp(libs.koin.ksp)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.android.driver)
                implementation(libs.koin.android)
                implementation(libs.androidx.lifecycle.viewmodel.ktx)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by getting {
            dependencies {
                implementation(libs.native.driver)
            }

            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }

    ksp {
        arg("KOIN_CONFIG_CHECK","true")
    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.example.noteapplication.database")
        }

    }

}



android {
    namespace = "com.example.noteapplication"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}