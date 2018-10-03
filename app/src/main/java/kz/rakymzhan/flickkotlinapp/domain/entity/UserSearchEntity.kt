package kz.rakymzhan.flickkotlinapp.domain.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "search", indices = [Index(value = ["text"], unique = true)])
data class UserSearchEntity (
        @PrimaryKey(autoGenerate = true)
        var id: Int?,

        var text: String = ""
)