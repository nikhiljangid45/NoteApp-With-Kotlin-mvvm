package com.example.noteappwithkotlinmvvm.repository

import com.example.noteappwithkotlinmvvm.databases.NoteDatabase
import com.example.noteappwithkotlinmvvm.models.Note

class NoteRepository(private val db :NoteDatabase) {

    suspend fun insertNote(note : Note)= db.getNoteDao().insertNote(note)
    suspend fun deleteNote(note :Note) = db.getNoteDao().deleteNote(note)
    suspend fun updateNote(note :Note) = db.getNoteDao().updateNote(note)

    fun getAllNote () = db.getNoteDao().getAllNotes()
    fun searchNote(query :String?) = db.getNoteDao().searchNote(query)


}