plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

repositories {
    google()
    mavenCentral()
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.packageName
        minSdk = Config.minSDK
        targetSdk = Config.targetSDK
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.material)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.fragment)

    // Navigation
    implementation(Dependencies.Navigation.navFragmentKtx)
    implementation(Dependencies.Navigation.navUiKtx)

    // Lifecycle
    implementation(Dependencies.Lifecycle.viewModel)
    implementation(Dependencies.Lifecycle.viewModelSavedState)
    implementation(Dependencies.Lifecycle.livedata)
    implementation(Dependencies.Lifecycle.commonJava)

    // Moshi
    implementation(Dependencies.Network.moshi)
    implementation(Dependencies.Network.moshiKotlin)
    kapt(Dependencies.Network.moshiKapt)

    // Retrofit
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.retrofitMoshiConverter)
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

    // Glide
    implementation(Dependencies.Glide.glide)
    annotationProcessor(Dependencies.Glide.glideAnnProcessor)

    // Timber
    implementation(Dependencies.Logging.timber)

    // ViewBinding
    implementation(Dependencies.ViewBinding.vbPropertyDelegate)


    // Adapter delegates
    implementation(Dependencies.RecyclerViewHelpers.adapterDelegates)

    // Paging
    implementation(Dependencies.RecyclerViewHelpers.paging)

    // Tests
    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.androidJUnit)
    androidTestImplementation(Dependencies.Test.espresso)
}