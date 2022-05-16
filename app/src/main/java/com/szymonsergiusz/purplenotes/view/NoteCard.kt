package com.szymonsergiusz.purplenotes.view

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.szymonsergiusz.purplenotes.notes.Note



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteCard(note: Note) {
    val width = 96.dp
    val squareSize = 48.dp

    val swipeableState = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1)

    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.swipeable(
            state = swipeableState,
            anchors = anchors,
            thresholds = { _, _ -> FractionalThreshold(0.3f) },
            orientation = Orientation.Horizontal
        )
    ) {
        Column() {
            NoteTitle(note = note)
            NoteDesc(note = note)
        }

    }
}


@Composable
fun NoteTitle(note: Note) {
    val title = note.title
    Text(
        modifier = Modifier.padding(5.dp),
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun NoteDesc(note: Note) {

    val desc = note.desc
    Text(
        modifier = Modifier.padding(10.dp), text = desc
    )

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


@Composable
@Preview
fun NoteCardPreview() {
    NoteCard(note = Note(title = "Tytuł", desc = "opis opis opis opis opis opis opis opis opis "))
}