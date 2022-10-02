package database

import androidx.lifecycle.LiveData
import androidx.room.*
import model.Notes

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(notes: Notes)

    @Query("SELECT * FROM Notes ORDER BY id ASC")
    fun readAllData(): LiveData<List<Notes>>

    @Delete
    suspend fun deleteNote(notes: Notes)

}