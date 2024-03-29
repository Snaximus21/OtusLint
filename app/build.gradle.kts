plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("io.gitlab.arturbosch.detekt") version "1.18.1"
}

android {
    namespace = "otus.android.homeworklint"
    compileSdk = 34

    defaultConfig {
        applicationId = "otus.android.homeworklint"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    lint {
        // Указываем список правил, которые нужно включить
        abortOnError = false // Параметр isAbortOnError отвечает за остановку сборки при обнаружении ошибок
        warningsAsErrors = true // Превращаем предупреждения в ошибки, чтобы они также останавливали сборку

        enable.add("DatabaseOnUIThread")

        lintConfig = file("lint.xml")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    compileOnly(libs.kotlin.stdlib)

    compileOnly(libs.lint.api)
    compileOnly(libs.lint.checks)

    testImplementation(libs.lint)
    testImplementation(libs.lint.tests)
    testImplementation(libs.testutils)
}