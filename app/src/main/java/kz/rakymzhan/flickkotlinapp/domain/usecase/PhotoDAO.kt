package kz.rakymzhan.flickkotlinapp.domain.usecase

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single
import kz.rakymzhan.flickkotlinapp.domain.entity.PhotoEntity

@Dao
interface PhotoDAO {


    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun saveAll(entities: List<PhotoEntity>)


    @Query("SELECT * FROM photo")
    fun getAllPhotos(): LiveData<List<PhotoEntity>>


    @Query("SELECT * FROM photo WHERE id = :id")
    fun getPhotoById(id: String): Single<PhotoEntity>
}