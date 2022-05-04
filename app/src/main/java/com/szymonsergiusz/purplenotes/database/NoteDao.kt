package com.szymonsergiusz.purplenotes.database;

import androidx.lifecycle.LiveData
import androidx.room.*

import com.szymonsergiusz.purplenotes.notes.Note;

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAll(): LiveData<List<Note>>

    @Insert
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("DELETE FROM note")
    fun deleteAllNotes()

}

