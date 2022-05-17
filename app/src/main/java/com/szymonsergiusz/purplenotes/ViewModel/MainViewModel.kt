package com.szymonsergiusz.purplenotes.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import com.szymonsergiusz.purplenotes.database.AppDatabase
import com.szymonsergiusz.purplenotes.database.NoteRepository
import com.szymonsergiusz.purplenotes.notes.Note

class MainViewModel(application: Application) {
    private val repository: NoteRepository

    var allNotes: LiveData<List<Note>>


    init {
        val appDb = AppDatabase.getDatabase(application)
        val noteDao = appDb.noteDao()
        repository = NoteRepository(noteDao)

        allNotes = repository.allNotes

    }

    fun insertNote(note: Note) {
        repository.insertNote(note)
    }
    fun deleteNote(note: Note) {
        repository.deleteNote(note)
    }
    fun updateNote(note: Note) {

        val titleCopy = note.title
        note.title = "test"

        val x = note.copy(title = titleCopy)

        repository.updateNote(x)
//        allNotes = repository.allNotes
    }

    fun deleteAllNotes() {
        repository.deleteAll()
    }

}