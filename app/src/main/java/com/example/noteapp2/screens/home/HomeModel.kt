package com.example.noteapp2.screens.home

import com.example.noteapp2.database.NoteDao
import com.example.noteapp2.database.NoteEntity

class HomeModel(private val appDao: NoteDao) {

    fun getAllNotes():List<NoteEntity>{
        return appDao.getAllNotes()
    }

    fun delete(noteEntity: NoteEntity){
        appDao.deleteNote(noteEntity)
    }


}