package com.example.notesmvvmapp.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesmvvmapp.database.NoteDataBase
import com.example.notesmvvmapp.repository.NotesRepositories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(appication: Application) : AndroidViewModel(appication) {
    private val repository: NotesRepositories

    val allNotes: LiveData<List<Note>>

    init {
        val dao = NoteDataBase.getDatabase(appication).getNoteDao()
        repository = NotesRepositories(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note: Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNote(note: Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun updateNote(note: Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

}