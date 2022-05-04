package com.szymonsergiusz.purplenotes.notes

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.DoneOutline
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteComponent(note: Note) {

    Surface(color = MaterialTheme.colors.primary, modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Row {

            var priority by rememberSaveable {
                mutableStateOf(note.priority)
            }


            Column() {
                Row {
                    IconButton(
                        modifier = Modifier.padding(0.dp)
                            .align(Alignment.CenterVertically)
                        ,
                        onClick = { if (priority != 2) priority++ else priority=0 }) {
                        val favIcon = Icons.Filled.Favorite
                        val notFavIcon = Icons.TwoTone.Favorite

                        val priorityNotDone = Icons.Outlined.DoneOutline
                        val priorityDone = Icons.Filled.Done
                        val priorityImportant = Icons.Filled.PriorityHigh
                        val iconsList = listOf<ImageVector>(priorityNotDone, priorityImportant, priorityDone)


                        Icon(imageVector = iconsList[priority], contentDescription = "not favorite")
                    }
                    NoteTitle(note)
                }

                NoteDesc(note)
            }


        }

    }
}

@Composable
fun NoteTitle(note: Note) {
    val title = note.title
    var text = rememberSaveable { mutableStateOf(title)}

    TextField(
        modifier = Modifier.fillMaxWidth()
        ,
        value = text.value, onValueChange = {
        note.title = it
        text.value = it
    })
}

@Composable
fun NoteDesc(note: Note) {

    val desc = note.desc
    var text = rememberSaveable {
        mutableStateOf(desc)
    }
    TextField(modifier = Modifier
        .fillMaxWidth()

        ,
        value = text.value, onValueChange = {
        changeNote(it, note)
        text.value = it
    })
}

fun changeNote(desc: String, note: Note) {
    note.desc = desc
}

@Preview(showBackground = true)
@Composable
fun NoteComponent0Preview(){
    NoteComponent(note = Note(0, "Test1", "lorem ipsum lorem ipsum lorem ipsum", 0))
}
@Preview(showBackground = true)
@Composable
fun NoteComponent1Preview(){
    NoteComponent(note = Note(1, "Test2", "lorem ipsum lorem ipsum lorem ipsum", 1))
}
@Preview(showBackground = true)
@Composable
fun NoteComponent2Preview(){

    NoteComponent(note = Note(2, "Test3", "lorem ipsum lorem ipsum lorem ipsum", 2))
}