package com.example.noteme.repo

import com.example.noteme.database.NotesDatabase
import com.example.noteme.model.Notes

class NotesRepo(private val notesDatabase: NotesDatabase) {

    suspend fun insert(notes: Notes) = notesDatabase.dao.insert(notes)
    suspend fun update(notes: Notes) = notesDatabase.dao.update(notes)
    suspend fun delete(notes: Notes) = notesDatabase.dao.delete(notes)
    suspend fun getAllNotes() = notesDatabase.dao.getAllNote()
}