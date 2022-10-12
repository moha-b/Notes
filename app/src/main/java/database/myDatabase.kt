package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import model.Notes

@Database(entities = [Notes::class], version = 9, exportSchema = false)
abstract class myDatabase : RoomDatabase() {

    abstract fun dao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: myDatabase? = null

        fun getDatabase(context: Context): myDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    myDatabase::class.java,
                    "user_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}