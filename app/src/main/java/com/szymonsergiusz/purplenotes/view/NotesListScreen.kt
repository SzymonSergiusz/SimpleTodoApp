package com.szymonsergiusz.purplenotes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.szymonsergiusz.purplenotes.ViewModel.MainViewModel
import com.szymonsergiusz.purplenotes.notes.AddNoteDialog
import com.szymonsergiusz.purplenotes.notes.Note

@Composable
fun NotesListScreen(
    actionOfFloatButton: MutableState<Boolean>,
    viewModel: MainViewModel,

) {
    val itemsList by viewModel.allNotes.observeAsState(listOf())
    AddNoteDialog(actionOfFloatButton, viewModel)
    NotesList(notes = itemsList)
}