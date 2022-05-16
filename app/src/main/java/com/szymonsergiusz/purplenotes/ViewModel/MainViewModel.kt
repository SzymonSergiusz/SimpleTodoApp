package com.szymonsergiusz.purplenotes.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import com.szymonsergiusz.purplenotes.database.AppDatabase
import com.szymonsergiusz.purplenotes.database.NoteRepository
import com.szymonsergiusz.purplenotes.notes.Note
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(application: Application) {
    private val _revealedCardIdsList = MutableStateFlow(listOf<Int>())
    val revealedCardIdsList: List<Int> = mutableListOf()
    val allNotes: LiveData<List<Note>>
    private val repository: NoteRepository


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
        repository.updateNote(note)
    }

    fun deleteAllNotes() {
        repository.deleteAll()
    }

    fun onItemExpanded(cardId: Int) {
        if (_revealedCardIdsList.value.contains(cardId)) return
        _revealedCardIdsList.value = _revealedCardIdsList.value.toMutableList().also { list ->
            list.add(cardId)
        }
    }

    fun onItemCollapsed(cardId: Int) {
        if (!_revealedCardIdsList.value.contains(cardId)) return
        _revealedCardIdsList.value = _revealedCardIdsList.value.toMutableList().also { list ->
            list.remove(cardId)
        }
    }
}