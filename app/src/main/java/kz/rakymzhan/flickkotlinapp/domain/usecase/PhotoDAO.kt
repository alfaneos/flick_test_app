package kz.rakymzhan.flickkotlinapp.domain.usecase

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Single
import kz.rakymzhan.flickkotlinapp.domain.entity.PhotoEntity

@Dao
interface PhotoDAO {


    @Insert
    fun saveAll(entities: ArrayList<PhotoEntity>)

    @Query("SELECT * FROM photo")
    fun getAllPhotos(): ArrayList<PhotoEntity>


    @Query("SELECT * FROM photo WHERE id = :id")
    fun getPhotoById(id: String): Single<PhotoEntity>
}