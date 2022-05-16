package com.szymonsergiusz.purplenotes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.szymonsergiusz.purplenotes.ViewModel.MainViewModel
import com.szymonsergiusz.purplenotes.notes.AddNoteDialog
import com.szymonsergiusz.purplenotes.view.DraggableCard
import com.szymonsergiusz.purplenotes.view.NoteCard



const val ACTION_ITEM_SIZE = 56
const val CARD_HEIGHT = 56
const val CARD_OFFSET = 168f // we have 3 icons in a row, so that's 56 * 3
@Composable
fun NotesListScreen(
    actionOfFloatButton: MutableState<Boolean>,
    viewModel: MainViewModel,
) {
    val itemsList by viewModel.allNotes.observeAsState(listOf())
    val revealedCardIds = viewModel.revealedCardIdsList
    AddNoteDialog(actionOfFloatButton, viewModel)


    LazyColumn() {
        items(items = itemsList) { item ->
            Box(modifier = Modifier.fillMaxWidth()) {
                ActionsRow(
                    iconSize = 20.dp,
                    onDelete = { viewModel.deleteNote(item) },
                    onEdit = { viewModel.updateNote(item) },
                    onArchive = {}
                )
                DraggableCard(
                    card = item,
                    isRevealed = revealedCardIds.contains(item.id),
                    cardHeight = CARD_HEIGHT.dp,
                    cardOffset = CARD_OFFSET,
                    onExpand = { viewModel.onItemExpanded(item.id) },
                    onCollapse = {viewModel.onItemCollapsed(item.id)})

            }
        }
    }

}

@Composable
fun ActionsRow(iconSize: Dp, onDelete: () -> Unit, onEdit: ()-> Unit, onArchive: () -> Unit) {

    //to wklejenie w celu naukowym poprawić na swoje

    Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        IconButton(
            modifier = Modifier.size(iconSize),
            onClick = {
                onDelete()
            },
            content = {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    tint = Color.Gray,
                    contentDescription = "delete action",
                )
            }
        )
        IconButton(
            modifier = Modifier.size(iconSize),
            onClick = { onEdit },
            content = {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    tint = Color.Gray,
                    contentDescription = "edit action",
                )
            },
        )
        IconButton(
            modifier = Modifier.size(iconSize),
            onClick = onArchive,
            content = {
                Icon(
                    imageVector = Icons.Filled.Archive,
                    tint = Color.Gray,
                    contentDescription = "archive action",
                )
            }
        )
    }
}


@Composable
@Preview
fun ActionRowPreview() {
    ActionsRow(iconSize = 20.dp, onDelete = { /*TODO*/ }, onEdit = { /*TODO*/ }) {
        
    }
}
