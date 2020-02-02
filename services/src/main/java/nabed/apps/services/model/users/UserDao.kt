package nabed.apps.services.model.users

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE username =:username AND password =:password")
    suspend fun getUser(username: String, password:String): RoomUser

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addNewUser(user: RoomUser):Long

}