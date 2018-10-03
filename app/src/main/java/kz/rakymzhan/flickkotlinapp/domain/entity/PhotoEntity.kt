package kz.rakymzhan.flickkotlinapp.domain.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "photo", indices = [Index(value = ["id"], unique = true)])
data class PhotoEntity(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: String,

        @ColumnInfo(name = "owner")
        val owner: String,

        @ColumnInfo(name = "secret")
        val secret: String,

        @ColumnInfo(name = "server")
        val server: String,

        @ColumnInfo(name = "farm")
        val farm: Int,

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "isfriend")
        val isfriend: Int,

        @ColumnInfo(name = "isfamily")
        val isfamily: Int,

        @ColumnInfo(name = "is_primary")
        val is_primary: Int,

        @ColumnInfo(name = "has_comment")
        val has_comment: Int
)