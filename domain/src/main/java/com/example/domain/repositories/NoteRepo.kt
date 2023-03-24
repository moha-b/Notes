package com.example.domain.repositories

import androidx.lifecycle.LiveData
import com.example.domain.models.Note

interface NoteRepo {

    suspend fun getAllNotes(): LiveData<List<Note>>

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)
}