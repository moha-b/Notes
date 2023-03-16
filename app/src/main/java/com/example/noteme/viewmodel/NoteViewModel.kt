package com.example.noteme.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteme.model.Notes
import com.example.noteme.repo.NotesRepo
import kotlinx.coroutines.launch

class NoteViewModel(application: Application, private val repo: NotesRepo) :
    AndroidViewModel(application) {

    fun insert(notes: Notes) = viewModelScope.launch {
        repo.insert(notes)
    }

    fun update(notes: Notes) = viewModelScope.launch {
        repo.update(notes)
    }

    fun delete(notes: Notes) = viewModelScope.launch {
        repo.insert(notes)
    }

    fun getAllNotes() = viewModelScope.launch {
        repo.getAllNotes()
    }

}