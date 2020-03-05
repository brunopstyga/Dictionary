package com.example.practicaskotlin.presentation.view

import android.R.attr.path
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.*
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaskotlin.BuildConfig
import com.example.practicaskotlin.R
import com.example.practicaskotlin.business.viewmodel.DataViewModel
import com.example.practicaskotlin.data.model.room.Data
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.view.*
import java.io.File
import javax.inject.Inject


class ListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var dataViewModel: DataViewModel
    private var recyclerView: RecyclerView? = null
    private lateinit var adapter: DatadListAdapter
    private var listData = listOf<Data>()
    private lateinit var imageUri : Uri
    private lateinit var imgPath : String





    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataViewModel = activity?.let {
            ViewModelProviders.of(this, viewModelFactory)[DataViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        dataViewModel.dataGet().observe(this, Observer {
            adapter.setData(it)
            writeDataUri(it)

        })
        enableSwipe()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.textViewSend.setOnClickListener {
            sendFile(imageUri)
        }
        view.textViewBack.setOnClickListener {
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

    private fun writeDataUri(data: List<Data>): Uri {
        val folder = File("${context!!.getExternalFilesDir(Environment.DIRECTORY_DCIM)}")
        folder.mkdirs()

        val file = File(folder, "DataList.txt")
        if (file.exists())
            file.delete()
        file.createNewFile()
            imageUri = FileProvider.getUriForFile(
                activity!!,
                BuildConfig.APPLICATION_ID + getString(R.string.file_provider_name),
                file
            )
//        /storage/emulated/0/Android/data/com.example.practicaskotlin/files/DCIM/DataList.txt
        imgPath = file.absolutePath
        file.printWriter().use {
            out ->
            data.forEach {
                out.println("${it.dataEnglish} -----> ${it.dataSpanish}")
            }
        }

        return imageUri
    }

    private fun sendFile(file: Uri){
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "vnd.android.cursor.dir/email"
        val to = arrayOf("bpstyga@gmail.com")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to)
        emailIntent.putExtra(Intent.EXTRA_STREAM, file)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
        startActivity(Intent.createChooser(emailIntent, "Send email..."))
    }

    fun removeFragment(){
        val manager = activity?.supportFragmentManager
        val listFragment = manager?.findFragmentByTag("ListFragment")
        val transaction = manager?.beginTransaction()
        manager?.popBackStack()
        transaction?.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}