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
import com.szymonsergiusz.purplenotes.notes.Note

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