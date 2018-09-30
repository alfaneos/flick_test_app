package kz.rakymzhan.flickkotlinapp.data.di

import android.arch.persistence.room.Room
import kz.rakymzhan.flickkotlinapp.data.db.PhotoDatabase
import kz.rakymzhan.flickkotlinapp.data.network.`interface`.FlickrAPI
import kz.rakymzhan.flickkotlinapp.data.repository.PhotoRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module


val localAndroidDatasourceModule = module(override = true) {


    single {
        Room.databaseBuilder(androidApplication(),
                PhotoDatabase::class.java, "photo-db")
                .build()
    }

    single { get<PhotoDatabase>().photoDAO() }


    single { PhotoRepositoryImpl(get()) }

    single { FlickrAPI.create() }

}