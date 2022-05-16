package com.szymonsergiusz.purplenotes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.szymonsergiusz.purplenotes.notes.Note
import com.szymonsergiusz.purplenotes.view.NoteComponent

@Composable
fun NotesList(notes: List<Note>) {
    LazyColumn() {
        items(items = notes) {
            NoteComponent(it)
        }
    }
}