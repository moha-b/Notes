package com.example.domain.usecases

import com.example.domain.repositories.NoteRepo

class GetAllNotes(private val repo: NoteRepo) {
    suspend operator fun invoke() = repo.getAllNotes()
}