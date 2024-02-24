plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")

    id("org.jetbrains.kotlin.plugin.serialization")
    id ("com.google.devtools.ksp")
}

android {
    namespace = "com.dm.coyago.zentunes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dm.coyago.zentunes"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding= true
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //navigation component
    val nav_version = "2.5.3"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    //coil manejo de imagenes
    implementation("io.coil-kt:coil:2.5.0")
    implementation("com.airbnb.android:lottie:6.0.0")

            ///KTOR
    //ktor client api service
    var ktorVersion = "2.2.4"
    implementation ("io.ktor:ktor-client-core:$ktorVersion")

    implementation("io.ktor:ktor-client-android:$ktorVersion")

// HTTP engine: The HTTP client used to perform network requests.
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
// Logging
    implementation ("io.ktor:ktor-client-logging:$ktorVersion")


    // The serialization engine used to convert objects to and from JSON.
    implementation ("io.ktor:ktor-client-json:$ktorVersion")
    implementation ("io.ktor:ktor-client-serialization:$ktorVersion")
    // content Negotiation
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    // Json
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    //XML
    implementation("io.ktor:ktor-serialization-kotlinx-xml:$ktorVersion")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    //Retrofit (API)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // To use Kotlin Symbol Processing (KSP)
    ksp ("androidx.room:room-compiler:2.5.0")

//Room

    implementation ("androidx.room:room-runtime:2.5.0")
    annotationProcessor ("androidx.room:room-compiler:2.5.0")





}