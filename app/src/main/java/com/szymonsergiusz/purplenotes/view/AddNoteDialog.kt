package com.szymonsergiusz.purplenotes.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import com.szymonsergiusz.purplenotes.ViewModel.MainViewModel

@Composable
fun AddNoteDialog(
    openDialog: MutableState<Boolean>,
    viewModel: MainViewModel
) {
    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }, content = {

            Column(modifier = Modifier.background(Color.White)) {


                var title by remember { mutableStateOf("") }
                TextField(value = title, onValueChange = { title = it })


                var description by remember { mutableStateOf("") }
                TextField(value = description, onValueChange = { description = it })
                Button(onClick = {
                    viewModel.insertNote(Note(title = title, desc = description))
                    openDialog.value = false
                }) {
                    Text("Add")
                }
            }

        })
    }
}
@Composable
fun EditNoteDialog(
    note: Note,
    viewModel: MainViewModel,
    dialogState: MutableState<Boolean>
) {
    if (dialogState.value) {
        Dialog(onDismissRequest = { dialogState.value = false }, content = {

            Column(modifier = Modifier.background(Color.White)) {


                var newTitle by remember { mutableStateOf(note.title) }
                TextField(value = newTitle, onValueChange = { newTitle = it })


                var newDescription by remember { mutableStateOf(note.desc) }
                TextField(value = newDescription, onValueChange = { newDescription = it })
                Button(onClick = {

                    note.apply {
                        title = newTitle
                        desc = newDescription
                    }

                    viewModel.updateNote(note)

                    dialogState.value = false
                }) {
                    Text("Save")
                }
            }

        })
    }
}