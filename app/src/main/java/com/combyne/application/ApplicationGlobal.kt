package com.combyne.application

import androidx.multidex.MultiDexApplication
import com.combyne.cache.di.CachemanagerInjector
import com.combyne.main.mainactivity.di.MainActivityInjector
import com.combyne.repository.di.RepositoryInjector
import com.combyne.showmanager.addmovie.di.AddMovieInjector
import com.combyne.showmanager.movies.di.MoviesInjector
import com.combyne.showmanager.shows.di.ShowsInjector
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.util.*

class ApplicationGlobal : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        initialKoin()

    }

    private fun initialKoin() {
        startKoin {
            androidLogger(Level.ERROR).androidContext(this@ApplicationGlobal)
                .modules(CachemanagerInjector.provideDependencies())
                .modules(RepositoryInjector.provideDependencies())
                .modules(MainActivityInjector.provideDependencies())
                /*Show Manager*/
                .modules(ShowsInjector.provideDependencies())
                .modules(AddMovieInjector.provideDependencies())
                .modules(MoviesInjector.provideDependencies())
        }
    }

}