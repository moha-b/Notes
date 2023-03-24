package com.example.domain.usecases

import com.example.domain.models.Note
import com.example.domain.repositories.NoteRepo

class UpdateNote(private val repo: NoteRepo) {
    suspend operator fun invoke(note: Note) = repo.updateNote(note)
}