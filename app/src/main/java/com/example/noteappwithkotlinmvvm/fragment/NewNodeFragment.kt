package com.example.noteappwithkotlinmvvm.fragment

import android.os.Bundle
import com.example.noteappwithkotlinmvvm.models.Note
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.noteappwithkotlinmvvm.MainActivity
import com.example.noteappwithkotlinmvvm.R
import com.example.noteappwithkotlinmvvm.adapter.NoteAdapter
import com.example.noteappwithkotlinmvvm.databinding.FragmentNewNodeBinding
import com.example.noteappwithkotlinmvvm.viewmodel.NoteViewModel


class NewNodeFragment : Fragment(R.layout.fragment_new_node) {
    private var _binding : FragmentNewNodeBinding? =null
    private val binding get() = _binding!!

    private lateinit var  noteAdapter : NoteAdapter
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var mView : View




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewNodeBinding.inflate(inflater,container,false)




         return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).noteViewModel

        mView = view
    }


    private fun saveNote(view: View){
        val noteTitle = binding.etNoteTitle.text.toString()
        val noteBody = binding.etNoteBody.text.toString()


        if (noteTitle.isNotEmpty()){
            val note  = Note(0,noteTitle,noteBody)
                noteViewModel.addNote(note)


            Toast.makeText(activity,"Note Save Successfuly", Toast.LENGTH_LONG).show()
          //  view?.findNavController()?.navigateUp()


         view.findNavController().navigate(R.id.action_newNodeFragment_to_homeFragment)

//            val fragmentManager = requireActivity().supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            val newNoteFragment = HomeFragment()
//            fragmentTransaction.replace(R.id.fragmentContainerView, newNoteFragment)
//            fragmentTransaction.addToBackStack(null) // Add the transaction to the back stack
//            fragmentTransaction.commit()


        }
        else{
            Toast.makeText(activity,"Please enter the Title ",Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {


        menu.clear()
        inflater.inflate(R.menu.menu_new_note,menu)
        super.onCreateOptionsMenu(menu, inflater)



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_save ->{

                        saveNote(mView)


            }
        }


        return super.onOptionsItemSelected(item)

    }
}
