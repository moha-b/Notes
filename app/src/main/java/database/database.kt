package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class database: RoomDatabase() {
    companion object {
        var data: database? = null

        @Synchronized
        fun getDatabase(context: Context): database {
            if (data == null) {
                data = Room.databaseBuilder(
                    context
                    , database::class.java
                    , "mydb.db"
                ).build()
            }
            return data!!
        }
    }

    abstract fun dao():NoteDao
}