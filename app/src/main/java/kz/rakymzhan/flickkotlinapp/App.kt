package kz.rakymzhan.flickkotlinapp

import android.app.Application
import kz.rakymzhan.flickkotlinapp.data.di.localAndroidDatasourceModule
import org.koin.android.ext.android.startKoin
import org.koin.standalone.StandAloneContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(localAndroidDatasourceModule))

    }
}