package nabed.apps.services.model.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "users",
    primaryKeys = ["id","username"],
    indices = [Index("id", "username")]
)
data class RoomUser(

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "password")
    val password: String

) {
    @ColumnInfo(name = "id")
    var id: Int = 0
}