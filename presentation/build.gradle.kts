import com.darkos.depend.Depend
import com.darkos.depend.applyDependencies
import com.darkos.depend.implementation

val depends = listOf(
    implementation(Libs.Kotlin.JDK),
    implementation(Libs.Kotlin.REFLECT),
    implementation(Libs.AndroidX.Activity.KTX),
    implementation(Libs.AndroidX.AppCompat.CORE),
    *Libs.Kodein.defaultAndroid
)

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-android-extensions")

    id("app-config-android")
    id("com.github.dcendents.android-maven")
}

group = "com.github.Darkos-den"

android {
    buildFeatures {
        dataBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    applyDependencies(depends)
}
