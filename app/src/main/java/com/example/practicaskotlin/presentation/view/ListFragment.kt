package com.example.practicaskotlin.presentation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaskotlin.R
import com.example.practicaskotlin.business.viewmodel.DataViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import java.lang.Exception
import javax.inject.Inject
import kotlin.math.log

class ListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var dataViewModel: DataViewModel
    private var recyclerView: RecyclerView? = null
    private lateinit var adapter: DatadListAdapter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataViewModel = activity?.let {
            ViewModelProviders.of(this, viewModelFactory)[DataViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        dataViewModel.dataGet().observe(this, Observer {
            adapter.setData(it)
        })
        enableSwipe()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarList.setNavigationIcon(R.drawable.ic_back)
        toolbarList.setTitle(R.string.app_name)
        toolbarList.setOnClickListener {
            removeFragment()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_list, container, false)
        val activity = activity as Context

//        val model : DataViewModel by activityViewModels<DataViewModel>()
        recyclerView = view.findViewById<RecyclerView>(R.id.customRecyclerView)
        adapter = DatadListAdapter(activity) { id, newWord ->
            dataViewModel.editWords(id, newWord)
        }
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        enableSwipe()
        return view
    }

    companion object {
        fun newInstance() =
            ListFragment()
    }


    private fun enableSwipe() {
        val simpleItemTouchCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition

                    if (direction == ItemTouchHelper.LEFT) {
                      dataViewModel.deleteData(adapter.data[position])
                    } else {
                        dataViewModel.deleteData(adapter.data[position])
                    }

                }
            }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    fun removeFragment(){
        val manager = activity?.supportFragmentManager
        val listFragment = manager?.findFragmentByTag("ListFragment")
        val transaction = manager?.beginTransaction()
        manager?.popBackStack()
        transaction?.commit()
    }



}