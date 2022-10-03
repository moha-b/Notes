package database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import model.Notes

class Repository(private val noteDao: NoteDao) {

    val readAllData: LiveData<List<Notes>> = noteDao.readAllData()

    suspend fun addNote(user: Notes){
        noteDao.addNote(user)
    }

    suspend fun search(search: String): Flow<List<Notes>>{
        return noteDao.search(search)
    }

    suspend fun updateNote(notes: Notes){
        noteDao.updateNote(notes)
    }

    suspend fun deleteNote(notes: Notes){
        noteDao.deleteNote(notes)
    }

}