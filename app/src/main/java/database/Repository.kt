package database

import androidx.lifecycle.LiveData
import model.Notes

class Repository(private val noteDao: NoteDao) {

    val readAllData: LiveData<List<Notes>> = noteDao.readAllData()

    suspend fun addNote(user: Notes){
        noteDao.addNote(user)
    }

    suspend fun deleteNote(notes: Notes){
        noteDao.deleteNote(notes)
    }

}