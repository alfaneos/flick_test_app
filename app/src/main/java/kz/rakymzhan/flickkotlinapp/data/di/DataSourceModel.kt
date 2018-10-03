package kz.rakymzhan.flickkotlinapp.data.di

import android.arch.persistence.room.Room
import kz.rakymzhan.flickkotlinapp.data.db.PhotoDatabase
import kz.rakymzhan.flickkotlinapp.data.network.client.PhotoNetworkClient
import kz.rakymzhan.flickkotlinapp.data.repository.PhotoRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module


val localAndroidDatasourceModule = module(override = true) {


    single {
        Room.databaseBuilder(androidApplication(),
                PhotoDatabase::class.java, "photo-db")
                .build()
    }

    single { get<PhotoDatabase>().photoDAO() }
    single { get<PhotoDatabase>().searchDAO() }


    single { PhotoRepositoryImpl(get()) }

    single { PhotoNetworkClient() }

}