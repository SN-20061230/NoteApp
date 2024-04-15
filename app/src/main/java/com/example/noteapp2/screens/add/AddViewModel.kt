package com.example.noteapp2.screens.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.noteapp2.database.NoteEntity

class AddViewModel(private val navController: NavController, private val model: AddModel) {
    private val _title = MutableLiveData("")
    val title: LiveData<String> = _title

    private val _description = MutableLiveData("")
    val description: LiveData<String> = _description

    var note = NoteEntity()

    fun add(note: NoteEntity) {
        model.addNote(note)
        navController.popBackStack()
    }
    fun editText(newTitle: String) {
        _title.value = newTitle
    }

    fun editAmount(newDescription: String) {
        if (newDescription.isEmpty()){
            _description.value = ""
        }
        else {
            _description.value = newDescription
        }
    }
}