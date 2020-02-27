package com.example.practicaskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.practicaskotlin.presentation.view.FragmentEdit
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    var fragmentmanager : FragmentManager? = null
// https://developer.android.com/jetpack/androidx/releases/lifecycle version

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentBlank = FragmentEdit.newInstance()
        createFragment(fragmentBlank);
    }

    fun createFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment,"fragmentEdit")
        fragmentTransaction.commit()

    }

}
