package kz.rakymzhan.flickkotlinapp.domain.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "photo")
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

        @ColumnInfo(name = "ispublic")
        val ispublic: Boolean,

        @ColumnInfo(name = "isfriend")
        val isfriend: Boolean,

        @ColumnInfo(name = "isfamily")
        val isfamily: Boolean,

        @ColumnInfo(name = "is_primary")
        val is_primary: Boolean,

        @ColumnInfo(name = "has_comment")
        val has_comment: Boolean
)