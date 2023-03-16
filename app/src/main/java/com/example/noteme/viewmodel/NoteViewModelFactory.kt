package com.example.noteme.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteme.repo.NotesRepo

class NoteViewModelFactory(val application: Application, private val repo: NotesRepo) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(application, repo) as T
    }
}