package com.example.noteapp2.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String? = null,
    var description: String? = null,
){
    constructor():this(0,"","")
}

@Dao
interface NoteDao{
    @Query("select * from NoteEntity  where id = :noteId")
    fun getNote(noteId:Int):NoteEntity

    @Insert
    fun addNote(note: NoteEntity)

    @Update
    fun editNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

    @Query("select * from NoteEntity")
    fun getAllNotes(): List<NoteEntity>

//    @Query("select * from ExpenseEntity order by date")
//    fun sortByDate():List<ExpenseEntity>
}

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getDao(): NoteDao

    companion object{
        private var instance : AppDataBase? = null
        fun getInstance(context: Context): AppDataBase{
            if (instance==null){
                instance = Room.databaseBuilder(context, AppDataBase::class.java, "app_dp").allowMainThreadQueries().build()
            }
            return instance!!
        }
    }
}