package com.example.noteapp2.screens.add

import com.example.noteapp2.database.NoteDao
import com.example.noteapp2.database.NoteEntity


class AddModel(private val appDao : NoteDao) {

    fun addNote(note: NoteEntity){
        appDao.addNote(note)

    }
}