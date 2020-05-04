package com.example.practicaskotlin.presentation.view

import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaskotlin.data.model.room.Data
import com.example.practicaskotlin.databinding.RecyclerviewItemBinding


class DatadListAdapter constructor(val context: Context):RecyclerView.Adapter<DatadListAdapter.WordViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    var data = emptyList<Data>()


    inner class WordViewHolder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
       fun bind(data: Data) {
          with(binding) {
            binding.textViewEnglish.text = Editable.Factory.getInstance().newEditable(data.dataEnglish)
            binding.textViewspanish.text = Editable.Factory.getInstance().newEditable(data.dataSpanish)
          }
       }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = RecyclerviewItemBinding.inflate(inflater, parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DatadListAdapter.WordViewHolder, position: Int) {
        holder.bind( data[position])
    }

    internal fun setData(data: List<Data>) {
        this.data = data
        notifyDataSetChanged()
    }
}
