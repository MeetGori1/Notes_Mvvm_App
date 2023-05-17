package com.example.notesmvvmapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesmvvmapp.models.Note
import com.example.notesmvvmapp.utilities.DATABASE_NAME

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDataBase:RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object{
        @Volatile
        private var INSTANCE:  NoteDataBase?=null

        fun getDatabase(context:Context):NoteDataBase{
            return INSTANCE?: synchronized(this){
                val insance= Room.databaseBuilder(context.applicationContext,NoteDataBase::class.java,DATABASE_NAME).build()
                INSTANCE=insance
                insance
            }
        }
    }
}