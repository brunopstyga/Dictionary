package com.example.practicaskotlin.presentation.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.practicaskotlin.R
import com.example.practicaskotlin.business.viewmodel.DataViewModel
import com.example.practicaskotlin.data.model.room.Data
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_edit.view.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class FragmentEdit : Fragment(){

    private lateinit var dataViewModel: DataViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataViewModel = activity?.let {
            ViewModelProvider(it).get(DataViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val model : DataViewModel by activityViewModels<DataViewModel>()
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_edit, container, false)
        view.Buttonlistdata.setOnClickListener {

            activity?.let{
                val fragmentBlank = ListFragment.newInstance()
                createFragment(fragmentBlank);
            }
        }
        view.addData.setOnClickListener {
            if(editTextEnglish.text.trim().length > 0 && editTextEsp.text.trim().length > 0) {
                model.existsWord(editTextEnglish.text.toString()).observe(this, Observer {
                    if (!it) {
                        model.inserData(
                            Data(
                                editTextEnglish.text.toString(),
                                editTextEsp.text.toString()
                            )
                        )
                        Toast.makeText(context, "Added Successfully", Toast.LENGTH_LONG).show()
                        editTextEnglish.setText("")
                        editTextEsp.setText("")
                    }else {
                        editTextEnglish.error = "Already Exists"
                    }
                })
            }
            else
                Toast.makeText(context, "Complete all fields", Toast.LENGTH_LONG).show()
        }
        return view
    }

    companion object {
        fun newInstance() =
            FragmentEdit()
    }

    fun createFragment(fragment: Fragment){
        val listfragment = activity?.supportFragmentManager?.beginTransaction()
        listfragment?.add(R.id.fragmentContainer, fragment,"ListFragment")
        listfragment?.addToBackStack(null)
        listfragment?.commit()
    }



}
