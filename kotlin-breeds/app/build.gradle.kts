plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
}

android {
    namespace = "es.tipolisto.breeds"
    compileSdk = 34

    defaultConfig {
        applicationId = "es.tipolisto.breeds"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //implementation ("com.squareup.okhttp3:logging-interceptor:4.4.0")
    //implementation ("com.squareup.okhttp3:okhttp:4.12.0")
    //implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    //implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")

    //Para ver las afotos
    //implementation ("com.squareup.picasso:picasso:2.71828")
    //Imágenes: https://developer.android.com/jetpack/compose/graphics/images?hl=es-419
    //Cargar imágenes con internet:
    //Recuerda poner en el manifest: <uses-permission android:name="android.permission.INTERNET"/>
    //La librería de coil nos va a permitir cargar imágenes de forma muy sencilla desde internet
    //Coil: https://github.com/coil-kt/coil#jetpack-compose
    implementation("io.coil-kt:coil-compose:2.5.0")

    //Navegación:https://developer.android.com/codelabs/jetpack-compose-navigation?hl=es-419#3
    /*
    1.Añade la dependencia
    2.Crea un archivo AppNavigation, dentro crea una clase un NaController y un NavHost dentro del navHost les defines las ventanas:
    @Composable
    fun AppNavigation(catsViewModel:CatsViewModel){
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = AppScreens.SplashScreen.route
        ) {
            composable("destino1"){
                GameCatScreen(navController,catsViewModel)
            }
            composable("destino2"){
                ListCatsScreen(navController,catsViewModel)
            }
            composable(
                route="destino_con_argumentos"+"/{reference_image_id}",
                arguments = listOf(
                    navArgument(name="reference_image_id"){type= NavType.StringType})
            )
            {
                val referenceImageId:String?=it.arguments?.getString("reference_image_id")
                requireNotNull(referenceImageId, { "No puede ser nulo" })
                DetailCatScreen(navController,catsViewModel,referenceImageId)
            }
       }
   }
    3.Para navegar a las pantallas desde los eventos on click pon :
    navController.navigate("destino")
    y si tiene argumentos pon:
     Column(modifier= Modifier
        .clickable {
            navController.navigate("destino"+"/${cat.reference_image_id}")
        }
      ){}
     */
    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    //Room
    //https://developer.android.com/training/data-storage/room?hl=es-419#kts
    /*
    1. se añade el pluging kotlin-kapt y las dependecias
    2 .Se crea la entidad, en nuestro caso crearemos RecordEntity como data class
    3 .Se crea el modelo dao de cada entidad que es un interfaz, en nuestro caso RecordDao
    4. Se crea la base de datos que es una clase abstracta que tiene de mienbro una función que devuelve la interface RecordDao
    5. Se crea en el main:
         val dataBase= Room.databaseBuilder(this, UserDatabase::class.java, "db_users" ).build()
         val dao=dataBase.UserDao()
         debes de usar un viewModel(dao)
         val viewModel=UsersViewModel(dao)
         y un navManager para la navegación
         NavManager(viewModel = viewModel)
     */
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")



    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}