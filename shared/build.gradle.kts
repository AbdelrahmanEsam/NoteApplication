plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
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
        ios(),
        iosArm64(),
        iosSimulatorArm64()
    )

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            export(project(":shared"))
        }
    }

    sourceSets {
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


        val iosMain by getting {
            dependencies {
                implementation(libs.native.driver)
            }
            dependsOn(commonMain)
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