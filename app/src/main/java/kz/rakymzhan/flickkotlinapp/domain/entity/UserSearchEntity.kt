package kz.rakymzhan.flickkotlinapp.domain.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(tableName = "search", indices = [Index(value = ["text"], unique = true)])
class UserSearchEntity {
        var text: String? = null
}