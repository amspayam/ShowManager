import com.combyne.extenstion.group
import org.gradle.kotlin.dsl.DependencyHandlerScope


// DP = Dependency Group

fun DependencyHandlerScope.androidXViewDG() {
    group(
        Libraries.material,
        Libraries.androidx_recyclerView,
        Libraries.androidx_cardView,
        Libraries.androidx_preferences,
        Libraries.androidx_appcompat,
        Libraries.androidx_legacySupportV4,
        Libraries.androidx_constraintLayout
    )
}

fun DependencyHandlerScope.retrofitAndGsonDG() {
    group(
        Libraries.retrofit,
        Libraries.retrofit_converterGson,
        Libraries.retrofit_converterScalars,
        Libraries.retrofit_adapterRxJava2,
        Libraries.okhttp3_loggingInterceptor,
        Libraries.okLog,
        Libraries.gson
    )
}

fun DependencyHandlerScope.rxDG() {
    group(
        Libraries.rxJava,
        Libraries.rxAndroid
    )
}

fun DependencyHandlerScope.coroutinesDG() {
    group(
        Libraries.coroutines_core,
        Libraries.coroutines_android
    )
}

fun DependencyHandlerScope.koinDG() {
    group(
        Libraries.koin_android,
        Libraries.koin_core,
        Libraries.koin_androidx_scope,
        Libraries.koin_androidx_viewmodel,
        Libraries.koin_android_architecture
    )
}

fun DependencyHandlerScope.navigationDG() {
    group(
        Libraries.androidx_navigation_runtime,
        Libraries.androidx_navigation_fragment,
        Libraries.androidx_navigation_ui
    )
}

fun DependencyHandlerScope.apolloDG() {
    group(
        Libraries.apollo_runtime,
        Libraries.apollo_coroutines_support,
        Libraries.apollo_rx2_support,
        Libraries.apollo_android_support
    )
}

fun DependencyHandlerScope.lifeCycleDG() {
    group(
        Libraries.androidx_lifecycle_viewmodel,
        Libraries.androidx_lifecycle_livedata
    )
}

fun DependencyHandlerScope.chuckDG() {
    "debugImplementation"(Libraries.chuckDebug)
    "releaseImplementation"(Libraries.chuckRelease)
}


fun DependencyHandlerScope.baseModuleDG() {
    /*Ktx*/
    Libraries.androidx_core_ktx
    /*Core*/
    koinDG()
    rxDG()
    coroutinesDG()
    retrofitAndGsonDG()
    apolloDG()
    navigationDG()
    lifeCycleDG()
    chuckDG()
    /*UI Kit*/
    androidXViewDG()
    navigationDG()
}