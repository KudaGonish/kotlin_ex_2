plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

dependencies {

    implementation(Android.CoreKtx)
    implementation(Retrofit.converter_gson)
}