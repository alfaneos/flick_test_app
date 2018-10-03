package kz.rakymzhan.flickkotlinapp.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import kz.rakymzhan.flickkotlinapp.domain.entity.PhotoEntity
import kz.rakymzhan.flickkotlinapp.domain.entity.UserSearchEntity
import kz.rakymzhan.flickkotlinapp.domain.usecase.PhotoDAO
import kz.rakymzhan.flickkotlinapp.domain.usecase.SearchDAO

@Database(entities = [PhotoEntity::class, UserSearchEntity::class], version = 2)
abstract class PhotoDatabase : RoomDatabase() {

    abstract fun photoDAO(): PhotoDAO
    abstract fun searchDAO(): SearchDAO

    companion object {
        val DATABASE_NAME = "photo-db"
    }
}