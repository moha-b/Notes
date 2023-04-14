package com.example.data.data_source

import androidx.room.Database
import com.example.domain.models.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase {
    abstract val dao: NoteDao

    companion object{
        const val DATABASE_NAME = "notes_db"
    }
}