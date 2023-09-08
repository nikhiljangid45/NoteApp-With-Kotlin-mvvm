package com.example.noteappwithkotlinmvvm

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.example.noteappwithkotlinmvvm.databases.NoteDatabase
import com.example.noteappwithkotlinmvvm.databinding.ActivityMainBinding
import com.example.noteappwithkotlinmvvm.repository.NoteRepository
import com.example.noteappwithkotlinmvvm.viewmodel.NoteViewModel
import com.example.noteappwithkotlinmvvm.viewmodel.NoteViewModelFactory


class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   setContentView(R.layout.ac)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
//
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragmentContainerView, HomeFragment()) // R.id.fragmentContainer is the ID of the FrameLayout in your layout
//            .commit()

   //  val navController = findNavController(this, R.id.fragmentContainerView)
    }

    private fun setUpViewModel() {
        val noteRepository = NoteRepository(NoteDatabase(this))
        val viewModelProviderFactory = NoteViewModelFactory(application,noteRepository)

        noteViewModel = ViewModelProvider(this,viewModelProviderFactory).get(NoteViewModel::class.java)
       // noteViewModel = ViewModelProvider(this,viewModelProviderFactory).get(NoteViewModel::class.java)

    }
}