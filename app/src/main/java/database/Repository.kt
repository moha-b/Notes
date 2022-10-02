package database

import androidx.lifecycle.LiveData
import database.NoteDao
import model.Notes

class Repository(private val noteDao: NoteDao) {

    val readAllData: LiveData<List<Notes>> = noteDao.readAllData()

    suspend fun addNote(user: Notes){
        noteDao.addUser(user)
    }

}