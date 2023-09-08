package com.example.noteappwithkotlinmvvm.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.noteappwithkotlinmvvm.databinding.NoteLayoutBinding
import com.example.noteappwithkotlinmvvm.fragment.HomeFragmentDirections
import com.example.noteappwithkotlinmvvm.models.Note
import java.util.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val itemBinding : NoteLayoutBinding ):
    RecyclerView.ViewHolder(itemBinding.root)
    private val differCallback = object : DiffUtil.ItemCallback<Note>()
    {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.noteBody ==  newItem.noteBody &&
                    oldItem.noteTitle == newItem.noteTitle
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }


    }
    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteViewHolder {
        return  NoteViewHolder(
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]
        holder.itemBinding.tvNoteTitle.text = currentNote.noteTitle
        holder.itemBinding.tvNoteBody.text = currentNote.noteBody

        val random = Random()
        val colour = Color.argb(
            255,random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        )

        holder.itemBinding.ibColour.setBackgroundColor(colour)
        holder.itemView.setOnClickListener(){



            val action = HomeFragmentDirections.actionHomeFragmentToUpdateNoteFragment(currentNote)
            holder.itemView.findNavController().navigate(action)



        }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}