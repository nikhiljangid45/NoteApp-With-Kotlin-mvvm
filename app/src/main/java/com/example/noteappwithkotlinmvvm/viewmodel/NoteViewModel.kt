package com.example.noteappwithkotlinmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappwithkotlinmvvm.models.Note
import com.example.noteappwithkotlinmvvm.repository.NoteRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel(app : Application , private val noteRepository: NoteRepository)  : AndroidViewModel(app)
{
    fun addNote(note: Note) = viewModelScope.launch {
        GlobalScope.launch {
            noteRepository.insertNote(note)

        }
    }

    fun deleteNote(note: Note) =viewModelScope.launch {

        GlobalScope.launch {
            noteRepository.deleteNote(note)

        }
    }

    fun updateNote(note :Note) =viewModelScope.launch {
        GlobalScope.launch {
            noteRepository.updateNote(note)

        }
    }

    fun getAllNotes() = noteRepository.getAllNote()
    fun searchNote(query :String) = noteRepository.searchNote(query)



}