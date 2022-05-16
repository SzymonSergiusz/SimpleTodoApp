package com.szymonsergiusz.purplenotes.view

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.spimport com.szymonsergiusz.purplenotes.notes.Note

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
//                    IconButton(
//                        modifier = Modifier
//                            .padding(0.dp)
//                            .align(Alignment.CenterVertically)
//                        ,
//                        onClick = { if (priority != 2) priority++ else priority=0 }) {
//                        val priorityNotDone = Icons.Outlined.DoneOutline
//                        val priorityDone = Icons.Filled.Done
//                        val priorityImportant = Icons.Filled.PriorityHigh
//                        val iconsList = listOf<ImageVector>(priorityNotDone, priorityImportant, priorityDone)
//
//
//                        Icon(imageVector = iconsList[priority], contentDescription = "not favorite")
//                    }
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
    Text(modifier = Modifier.padding(5.dp)
        ,text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    
//    var text = rememberSaveable { mutableStateOf(title)}
//
//    TextField(
//        modifier = Modifier.fillMaxWidth()
//        ,
//        value = text.value, onValueChange = {
//        note.title = it
//        text.value = it
//    })
}

@Composable
fun NoteDesc(note: Note) {

    val desc = note.desc
    Text(modifier = Modifier.padding(10.dp)
        ,text = desc)
    
//    val text = rememberSaveable {
//        mutableStateOf(desc)
//    }
//    
//    TextField(modifier = Modifier
//        .fillMaxWidth()
//
//        ,
//        value = text.value, onValueChange = {
//        changeNote(it, note)
//        text.value = it
//    })
}

@Preview(showBackground = true)
@Composable
fun NoteComponentPreview(){
    NoteComponent(note = Note(0, "Test1", "lorem ipsum lorem ipsum lorem ipsum", 0))
}
