package database

import androidx.room.*
import model.Notes

@Dao
interface NoteDao {

    @get:Query("select * from Notes")
    val allNotes: List<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(Notes:Notes)

    @Delete
    suspend fun delete(Notes: Notes)
}