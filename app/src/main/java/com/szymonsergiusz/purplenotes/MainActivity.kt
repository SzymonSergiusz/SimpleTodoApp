package com.szymonsergiusz.purplenotes

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.szymonsergiusz.purplenotes.ViewModel.MainViewModel
import com.szymonsergiusz.purplenotes.ui.theme.PurpleNotesTheme
import kotlinx.coroutines.launch

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
//    val allNotes by viewModel.allNotes.observeAsState(listOf())



    val openAddNoteDialog = remember { mutableStateOf(false)}

    val screenContent = remember {
        mutableStateOf(0)
    }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {

            val buttonModifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)

            Spacer(modifier = Modifier.height(5.dp))
            Button(modifier = buttonModifier,
                onClick = {
                screenContent.value = 0
                scope.launch { scaffoldState.drawerState.close() }}) {
                Text(text = "Notes")

            }

            Button(modifier = buttonModifier,
                onClick = {
                screenContent.value = 1
                scope.launch { scaffoldState.drawerState.close() }
            }) {
                Text(text = "Archived")
            }
            Button(modifier = buttonModifier,
                onClick = {
                screenContent.value = 2
                scope.launch { scaffoldState.drawerState.close() }
            }) {
                Text(text = "About app")
            }
        },
        topBar = {

//            CenterAlignedTopAppBar(
//                title = { Text("Centered TopAppBar") },
//                navigationIcon = {
//                    IconButton(onClick = { scope.launch { scaffoldState.drawerState.apply { if (isClosed) open() else close() } } }) {
//                    Icon(Icons.Filled.Menu, contentDescription = "Drawer")
//                }
//                }
//            )


            TopAppBar() {
                Button(onClick = { scope.launch { scaffoldState.drawerState.apply { if (isClosed) open() else close() } } }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Drawer")
                }
                Text(text = "Simple Notes App")
                Column() {
                    Spacer(modifier = Modifier.fillMaxWidth())
                    val dropdownState = remember {
                        mutableStateOf(false)
                    }
                    IconButton(onClick = { dropdownState.value= !dropdownState.value }) {
                        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "App Options")
                    }
                    DropdownMenu(expanded = dropdownState.value, onDismissRequest = { dropdownState.value = !dropdownState.value }) {
                        DropdownMenuItem(onClick = {
                            viewModel.deleteAllNotes()
                            dropdownState.value = !dropdownState.value
                        }) {
                            Text(text = "Delete all notes")
                        }
                    }
                }

            }
        },
    floatingActionButton = {
        FloatingActionButton(
            onClick = { openAddNoteDialog.value = true },
            backgroundColor = Color.Magenta) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Note", tint = Color.White)
        }
    },

        content = {

            when (screenContent.value) {
                0 -> {
                    NotesListScreen(openAddNoteDialog, viewModel)
                }
                1 -> {
                    ArchivedNotesScreen()
                }
                2 -> {
                    AboutAppScreen()
                }
            }


        })
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PurpleNotesTheme {
        ScreenSetup()
    }
}