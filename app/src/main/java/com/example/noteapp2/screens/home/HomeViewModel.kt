package com.example.noteapp2.screens.home

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.room.Query
import com.example.noteapp2.database.NoteEntity
import kotlinx.coroutines.coroutineScope

class HomeViewModel(private val navController: NavController, private val homeModel: HomeModel) {
    private var _list: MutableLiveData<List<NoteEntity>> = MutableLiveData(listOf())
    var list: LiveData<List<NoteEntity>> = _list



    fun getFullList() {
        _list.value = homeModel.getAllNotes()
    }

    fun onAdd() {
        val id = -1
        navController.navigate("add_screen/$id")
    }

    fun delete(noteEntity: NoteEntity) {
        homeModel.delete(noteEntity)
        getFullList()
    }

    fun update(noteEntity: NoteEntity) {
        navController.navigate("edit_screen/${noteEntity.id}")
    }


    fun getListSearch(s:String): LiveData<List<NoteEntity>> {
            _list.value = homeModel.getListSearch(s)
            return list
        }
    }


