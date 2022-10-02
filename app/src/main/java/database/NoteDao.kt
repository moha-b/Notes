package database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import model.Notes

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: Notes)

    @Query("SELECT * FROM Notes ORDER BY id ASC")
    fun readAllData(): LiveData<List<Notes>>

}