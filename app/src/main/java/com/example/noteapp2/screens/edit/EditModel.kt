package com.example.noteapp2.screens.edit

import com.example.noteapp2.database.NoteDao
import com.example.noteapp2.database.NoteEntity


class EditModel(private val appDao : NoteDao) {

    fun getNote(id:Int): NoteEntity {
        return appDao.getNote(id)
    }
    fun edit(expense: NoteEntity) {
        appDao.editNote(expense)
    }
}