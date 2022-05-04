package com.szymonsergiusz.purplenotes

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.szymonsergiusz.purplenotes.ViewModel.MainViewModel
import com.szymonsergiusz.purplenotes.notes.Note
import com.szymonsergiusz.purplenotes.notes.NoteComponent
import com.szymonsergiusz.purplenotes.ui.theme.PurpleNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PurpleNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenSetup()
                }
            }
        }
    }
}

@Composable
fun ScreenSetup(viewModel: MainViewModel =
                    MainViewModel(LocalContext.current.applicationContext as Application)) {
    val allNotes by viewModel.allNotes.observeAsState(listOf())

//    viewModel.deleteAllNotes()

//    NotesList(allNotes)
    
    NotesAppBar(
            onClick = { viewModel.insertNote(Note(title = "test", desc = "test lorem ipsum")) }
    )

    MainScreen(allNotes, viewModel)
}

@Composable
fun MainScreen(allNotes: List<Note>, viewModel: MainViewModel) {
    NotesList(notes = allNotes)
}

@Composable
fun NotesAppBar(onClick: () -> Unit) {
    TopAppBar() {
        Button(onClick = onClick)
         {
            Text(text = "Test")
        }
    }
}

@Composable
fun NotesList(notes: List<Note>) {
    // = List(10){ Note(title = "title $it", desc = "description $it")}
    LazyColumn() {
        items(items = notes) {
            NoteComponent(it)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PurpleNotesTheme {
        ScreenSetup()
    }
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    NotesAppBar({})
}