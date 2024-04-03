plugins {
    id ("java-library")
    id ("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {

    val lintVersion = "30.3.1"
    compileOnly ("org.jetbrains.kotlin:kotlin-stdlib:1.9.23")

    compileOnly ("com.android.tools.lint:lint-api:$lintVersion")
    compileOnly ("com.android.tools.lint:lint-checks:$lintVersion")

    testImplementation ("com.android.tools.lint:lint:$lintVersion")
    testImplementation ("com.android.tools.lint:lint-tests:$lintVersion")
    testImplementation ("com.android.tools:testutils:$lintVersion")
}