package com.example.noteappwithkotlinmvvm.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteappwithkotlinmvvm.MainActivity
import com.example.noteappwithkotlinmvvm.R
import com.example.noteappwithkotlinmvvm.adapter.NoteAdapter
import com.example.noteappwithkotlinmvvm.databinding.FragmentHomeBinding
import com.example.noteappwithkotlinmvvm.models.Note
import com.example.noteappwithkotlinmvvm.viewmodel.NoteViewModel


class HomeFragment : Fragment(R.layout.fragment_home) , SearchView.OnQueryTextListener{

    private var _binding : FragmentHomeBinding? =null
    private val binding get() = _binding!!

    private lateinit var  noteAdapter : NoteAdapter
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater,container,false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = (activity as MainActivity).noteViewModel
        setUpRecycleView()
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val newNoteFragment = NewNodeFragment()

        binding.fabAddNote.setOnClickListener(){


                it.findNavController().navigate(
                    R.id.action_homeFragment_to_newNodeFragment
                )



            }



    }

    private fun setUpRecycleView() {


        noteAdapter =  NoteAdapter()
        binding.recycleView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            adapter = noteAdapter

        }

        activity?.let {
            noteViewModel.getAllNotes().observe(
                viewLifecycleOwner,{
                    note -> noteAdapter.differ.submitList(note)
                    updateUI(note)
                }
            )
        }

    }

    private fun updateUI(note: List<Note>?) {
        if (note != null) {
            if (note.isNotEmpty()){
                binding.cardViewHome.visibility = View.GONE
                binding.recycleView.visibility = View.VISIBLE

            }else{
                binding.cardViewHome.visibility = View.VISIBLE
                binding.recycleView.visibility = View.GONE
            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.home_menu,menu)

        val mMenuSearch = menu.findItem(R.id.menu_search)
            .actionView as SearchView
        mMenuSearch.isSubmitButtonEnabled = false
        mMenuSearch.setOnQueryTextListener(this)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
       // searchNote(query)
        return false
    }



    override fun onQueryTextChange(newText: String?): Boolean {

        if (newText != null){
           searchNote(newText)
        }

        return true
    }



    private fun searchNote(query: String?) {

        val searchQuery = "%$query"
        noteViewModel.searchNote(searchQuery).observe(this,
            {list -> noteAdapter.differ.submitList(list)}
        )

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}