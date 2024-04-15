package com.example.noteapp2.screens.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.noteapp2.database.NoteEntity

class EditViewModel (private val navController: NavController, val id: Int, private val model: EditModel)  {

    private val _title = MutableLiveData("")
    val title: LiveData<String> = _title

    private val _description = MutableLiveData("")
    val description: LiveData<String> = _description

    var note = NoteEntity()


    init {
        if (id != -1) getNotes()
    }

    private fun getNotes() {
        val n = model.getNote(id)
        _title.value = n.title
        _description.value = n.description
        note = n
    }

    fun update(expense: NoteEntity) {
        updateNote()
        navController.popBackStack()

    }


    private fun updateNote() {
        val newTitle = title.value
        val newDescription = description.value
        val n = NoteEntity(note.id, newTitle, newDescription)
        model.edit(n)
    }

    fun updateText(newTitle: String) {
        _title.value = newTitle
    }

    fun updateAmount(newDescription: String) {
        if (newDescription.isEmpty()) {
            _description.value = ""
        }
        else{
            _description.value = newDescription

        }
    }


}