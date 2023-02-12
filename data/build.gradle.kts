plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
}

dependencies {

    implementation(project(":models"))

    kapt(Room.Compiler)
    implementation(Room.Ktx)
    implementation(Room.Runtime)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.converter_gson)
    implementation(Retrofit.okHttp)
}