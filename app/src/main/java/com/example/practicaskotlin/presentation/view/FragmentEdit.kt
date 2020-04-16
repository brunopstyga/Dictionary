package com.example.practicaskotlin.presentation.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.practicaskotlin.R
import com.example.practicaskotlin.business.viewmodel.DataViewModel
import com.example.practicaskotlin.data.model.room.Data
import com.example.practicaskotlin.databinding.FragmentEditBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_edit.view.*
import java.lang.Exception
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class FragmentEdit : DaggerFragment(){



    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var dataViewModel: DataViewModel



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataViewModel = activity?.let {
            ViewModelProviders.of(this, viewModelFactory)[DataViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        val binding =  DataBindingUtil.inflate<FragmentEditBinding>(inflater,R.layout.fragment_edit, container, false)
        binding.viewmodel = dataViewModel
        binding.lifecycleOwner = this
        binding.Buttonlistdata.setOnClickListener(View.OnClickListener {
            activity?.let{
                val fragmentBlank = ListFragment.newInstance()
                createFragment(fragmentBlank)
            }
        })

        binding.addData.setOnClickListener {
            if(editTextEnglish.text.trim().length > 0 && editTextEsp.text.trim().length > 0) {
                dataViewModel.existsWord(editTextEnglish.text.toString()).observe(viewLifecycleOwner, Observer {
                    if (!it) {
                        dataViewModel.inserData(
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
        return binding.root
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
