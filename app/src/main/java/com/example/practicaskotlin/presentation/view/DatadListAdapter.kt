package com.example.practicaskotlin.presentation.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaskotlin.MainActivity
import com.example.practicaskotlin.R
import com.example.practicaskotlin.business.viewmodel.DataViewModel
import com.example.practicaskotlin.data.model.room.Data

class DatadListAdapter internal constructor(val context: Context, val funcionQueRetornaAfterChange: ((id: Int, newEnglishWord: String) -> Unit)):RecyclerView.Adapter<DatadListAdapter.WordViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    var data = emptyList<Data>()
    private lateinit var dataViewModel: DataViewModel



    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordEnglish: EditText = itemView.findViewById(R.id.textViewEnglish)
        val wordSpanish: EditText = itemView.findViewById(R.id.textViewspanish)
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DatadListAdapter.WordViewHolder, position: Int) {
        val current = data[position]
        holder.wordEnglish.setText(current.dataEnglish)
        holder.wordEnglish.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                funcionQueRetornaAfterChange(current.id, s.toString())
//                Toast.makeText(context,"clicked",Toast.LENGTH_SHORT).show()
            }
        })
        holder.wordSpanish.setText(current.dataSpanish)
//        holder.wordSpanish.setOnClickListener {
//            val intent = Intent(context,EditList::class.java)
//            context.startActivity(intent)
////            Toast.makeText(context,"clicked",Toast.LENGTH_SHORT).show()
//        }
        holder.wordEnglish.setOnClickListener {
            Toast.makeText(context,"clicked",Toast.LENGTH_SHORT).show()
        }

    }

    internal fun setData(data: List<Data>) {
        this.data = data
        notifyDataSetChanged()
    }



}
