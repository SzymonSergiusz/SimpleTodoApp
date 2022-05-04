package com.szymonsergiusz.purplenotes.database

import androidx.lifecycle.LiveData
import com.szymonsergiusz.purplenotes.notes.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteRepository(private val noteDao: NoteDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    val allNotes: LiveData<List<Note>> = noteDao.getAll()


    fun insertNote(newNote: Note) {
        coroutineScope.launch(Dispatchers.IO) {
            noteDao.addNote(newNote)
        }
    }

    fun deleteNote(note: Note) {
        coroutineScope.launch(Dispatchers.IO) {
            noteDao.deleteNote(note)
        }
    }

    fun updateNote(note: Note) {
        coroutineScope.launch(Dispatchers.IO) {
            noteDao.updateNote(note)
        }
    }

    fun deleteAll() {
        coroutineScope.launch(Dispatchers.IO) {
            noteDao.deleteAllNotes()
        }
    }
}