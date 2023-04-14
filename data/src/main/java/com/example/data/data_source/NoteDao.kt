package com.example.data.data_source

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.domain.models.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    suspend fun getAllNotes(): LiveData<List<Note>>
    @Insert
    suspend fun insert(note: Note)
    @Update
    suspend fun update(note: Note)
    @Delete
    suspend fun delete(note: Note)

}