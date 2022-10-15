package database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import model.Notes

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(notes: Notes)

    @Query("SELECT * FROM Notes ORDER BY id ASC")
    fun readAllData(): LiveData<List<Notes>>

    @Query("select * from Notes where title like :search")
    fun search(search: String): LiveData<List<Notes>>

    @Query("select * from Notes where love like 1")
    fun addToFav(): LiveData<List<Notes>>

    @Update
    suspend fun updateNote(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)

}