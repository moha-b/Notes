package com.example.data.repositories

import androidx.lifecycle.LiveData
import com.example.data.data_source.NoteDao
import com.example.domain.models.Note
import com.example.domain.repositories.NoteRepo

class NoteRepositoriesImpl(private val dao: NoteDao): NoteRepo {

    override suspend fun getAllNotes(): LiveData<List<Note>> {
       return dao.getAllNotes()
    }

    override suspend fun insertNote(note: Note) {
        return dao.insert(note)
    }

    override suspend fun updateNote(note: Note) {
        return dao.update(note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.delete(note)
    }
}