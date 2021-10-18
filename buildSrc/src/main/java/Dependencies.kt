object Dependencies {

    object Android {
        const val coreKtx = "androidx.core:core-ktx:1.6.0"
        const val appCompat = "androidx.appcompat:appcompat:1.3.1"
        const val material = "com.google.android.material:material:1.4.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
        const val fragment = "androidx.fragment:fragment-ktx:1.3.6"
    }

    object Lifecycle {
        private const val lifecycleVersion = "2.3.1"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val viewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        const val commonJava = "androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion"
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    }

    object Navigation {
        private const val navVersion = "2.3.5"
        const val navFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$navVersion"
        const val navUiKtx = "androidx.navigation:navigation-ui-ktx:$navVersion"
    }

    object Glide {
        private const val glideVersion = "4.12.0"
        const val glide = "com.github.bumptech.glide:glide:$glideVersion"
        const val glideAnnProcessor = "com.github.bumptech.glide:compiler:$glideVersion"
    }

    object Network {
        private const val moshiVersion = "1.12.0"
        private const val retrofitVersion = "2.9.0"

        const val moshi = "com.squareup.moshi:moshi:$moshiVersion"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
        const val moshiKapt = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"

        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"

        const val auth = "net.openid:appauth:0.9.1"
    }

    object Logging {
        const val timber = "com.jakewharton.timber:timber:5.0.1"
    }

    object ViewBinding {
        const val vbPropertyDelegate = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.0-beta02"
    }

    object RecyclerViewHelpers {
        const val adapterDelegates = "com.hannesdorfmann:adapterdelegates4:4.3.0"
        const val paging = "androidx.paging:paging-runtime:3.0.1"
    }

    object Test {
        const val jUnit = "junit:junit:4.+"
        const val androidJUnit = "androidx.test.ext:junit:1.1.2"
        const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
    }
}