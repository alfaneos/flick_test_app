package kz.rakymzhan.flickkotlinapp.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import kz.rakymzhan.flickkotlinapp.domain.entity.PhotoEntity
import kz.rakymzhan.flickkotlinapp.domain.usecase.PhotoDAO

@Database(entities = [PhotoEntity::class], version = 2)
abstract class PhotoDatabase : RoomDatabase() {

    abstract fun photoDAO(): PhotoDAO

    companion object {
        val DATABASE_NAME = "photo-db"
    }
}