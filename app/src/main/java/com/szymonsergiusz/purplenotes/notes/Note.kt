package com.szymonsergiusz.purplenotes.notes

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="noteId")
    val id: Int = 0,

    @ColumnInfo(name="title")
    var title: String,

    @ColumnInfo(name="description")
    var desc: String,

    @ColumnInfo(name = "priority")
    var priority: Int = 0
    ) {

    fun updateTitle(newTitle: String) {
        title = newTitle
    }

    fun updateDesc(newDesc: String) {
        desc = newDesc
    }

    fun getNextPrior() {
        priority++
        if (priority == 3)
            priority = 0
    }

}
