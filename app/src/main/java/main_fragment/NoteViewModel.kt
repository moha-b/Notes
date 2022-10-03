package main_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import database.Repository
import database.myDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.Notes

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Notes>>
    private val repository: Repository

    init {
        val userDao = myDatabase.getDatabase(application).dao()
        repository = Repository(userDao)
        readAllData = repository.readAllData
    }

    fun addNote(note: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }

    fun updateNote(note: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

}