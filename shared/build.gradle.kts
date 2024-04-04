plugins {
    kotlin("multiplatform")
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.app.cash.sqldelight)
    alias(libs.plugins.touchlab.skie)
}

kotlin {

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
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
            isStatic = true
        }
    }



    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.sqldelight.coroutines.extensions)
            implementation(libs.koin.core)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        androidMain.dependencies {
            implementation(libs.android.driver)
            implementation(libs.koin.android)
            implementation(libs.androidx.lifecycle.viewmodel.ktx)
        }


        iosMain.dependencies {
            implementation(libs.native.driver)
        }
    }

    ksp {
        arg("KOIN_CONFIG_CHECK", "true")
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}