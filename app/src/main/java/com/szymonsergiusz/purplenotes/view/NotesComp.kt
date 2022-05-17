package com.szymonsergiusz.purplenotes.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.szymonsergiusz.purplenotes.ViewModel.MainViewModel
import com.szymonsergiusz.purplenotes.notes.EditNoteDialog
import com.szymonsergiusz.purplenotes.notes.Note

@Composable
fun NoteComponent(note: Note, mvvm: MainViewModel) {

    Surface(color = MaterialTheme.colors.primary, modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {


            Column() {
                NoteTitle(note = note)
                NoteDesc(note = note)
            }
        var isExpanded by remember { mutableStateOf(false)}
        Column(horizontalAlignment = Alignment.End) {
            IconButton(
                onClick = {
                          isExpanded = !isExpanded
                },
            ) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Options")

                val editDialog = remember { mutableStateOf(false)}
                DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded  = false }) {
                    DropdownMenuItem(onClick = { editDialog.value = !editDialog.value }) {
                        Text(text = "Edit")
                        if (editDialog.value) {
                            EditNoteDialog(
                                note = note,
                                viewModel = mvvm,
                                dialogState = editDialog)
                            isExpanded = !isExpanded
                        }

                    }
                    DropdownMenuItem(onClick = {mvvm.deleteNote(note) }) {
                        Text(text = "Delete")
                    }
                }
            }
        }
    }
}




//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun NoteComponent(note: Note) {
//
//    Surface(color = MaterialTheme.colors.primary, modifier = Modifier
//        .fillMaxWidth()
//        .padding(10.dp)) {
//
//        val width = 96.dp
//        val squareSize = 1000.dp
//        val swipeableState = rememberSwipeableState(initialValue = 0)
//        val sizePx = with(LocalDensity.current) { squareSize.toPx() }
//        val anchors = mapOf(0f to 0, sizePx to 1)
//
//
//
//        Box(            modifier = Modifier.swipeable(
//            state = swipeableState,
//            anchors = anchors,
//            thresholds = { _, _ -> FractionalThreshold(0.3f) },
//            orientation = Orientation.Horizontal)) {
//            Card(backgroundColor = MaterialTheme.colors.primary,
//                modifier = Modifier
//                    .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
//            ) {
//                Column() {
//                    NoteTitle(note = note)
//                    NoteDesc(note = note)
//                }
//
//            }
//        }
//    }
//}

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
//    NoteComponent(
//        note = Note(0, "Test1", "lorem ipsum lorem ipsum lorem ipsum"), mvvm = null
//    )
}
