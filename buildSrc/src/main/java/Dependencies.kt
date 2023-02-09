object Version {
    const val retrofit = "2.9.0"
    const val ok_http = "3.12.1"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    const val okHttp = "com.squareup.okhttp3:logging-interceptor:${Version.ok_http}"
}

object Android {
    const val CoreKtx = "androidx.core:core-ktx:1.7.0"
    const val AppCompat = "androidx.appcompat:appcompat:1.6.0"
    const val Material = "com.google.android.material:material:1.7.0"
    const val SwipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

}

object Koin {
    const val Core = "io.insert-koin:koin-core:3.3.2"
    const val Compat = "io.insert-koin:koin-android-compat:3.3.2"
    const val Android = "io.insert-koin:koin-android:3.3.2"
}

object Coil {
    const val Coil = "io.coil-kt:coil:2.2.2"
}

object Room {
    const val Compiler = "androidx.room:room-compiler:2.5.0"
    const val Ktx = "androidx.room:room-ktx:2.5.0"
    const val Runtime = "androidx.room:room-runtime:2.5.0"
}

object Tests {
    const val JUnit = ""
    const val ExtJUnit = "androidx.test.ext:junit:1.1.5"
    const val Espresso = "androidx.test.espresso:espresso-core:3.5.1"
}