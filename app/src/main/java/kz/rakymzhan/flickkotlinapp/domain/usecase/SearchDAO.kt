package kz.rakymzhan.flickkotlinapp.domain.usecase

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.database.Cursor
import kz.rakymzhan.flickkotlinapp.domain.entity.UserSearchEntity

@Dao
interface SearchDAO {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun saveSearch(entity: UserSearchEntity)

    @Query("SELECT * FROM search")
    fun getLastFiveSearchesCursor(): Cursor


    @Query("SELECT  * FROM search ORDER BY id desc LIMIT 5")
    fun getLastFiveSearches(): LiveData<List<UserSearchEntity>>


}