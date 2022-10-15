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
    lateinit var searchResult: LiveData<List<Notes>>
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

    fun search(title: String): LiveData<List<Notes>>{
        searchResult = repository.search(title)
        return searchResult
    }

    fun deleteNote(note: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }
    var favNotes: LiveData<List<Notes>> = repository.addToFav()

}