package com.example.domain.usecases

import com.example.domain.models.Note
import com.example.domain.repositories.NoteRepo

class InsertNote(private val repo: NoteRepo) {
    suspend operator fun invoke(note: Note) = repo.insertNote(note)
}