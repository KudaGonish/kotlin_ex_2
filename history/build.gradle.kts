plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}


dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("com.google.android.material:material:1.8.0")
    implementation(project(":models"))

    implementation (Koin.Compat)
            implementation (Koin.Core)
            implementation (Koin.Android)
}