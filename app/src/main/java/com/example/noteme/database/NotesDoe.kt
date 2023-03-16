package com.example.noteme.database

import androidx.room.*
import com.example.noteme.model.Notes

@Dao
interface NotesDoe {

    @Insert
    fun insert(note:Notes)

    @Update
    fun update(note: Notes)

    @Delete
    fun delete(note: Notes)

    @Query(value = "SELECT * FROM notes WHERE id = :id")
    fun getNote(id:Int): Notes
}