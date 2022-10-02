package database

import androidx.lifecycle.LiveData
import androidx.room.*
import model.Notes

@Dao
interface NoteDao {

    @get:Query("select * from Notes")
    val getAllNotesList: LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(Notes:Notes)

    @Delete
    suspend fun delete(Notes: Notes)
}