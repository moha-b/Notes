package com.example.noteme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.noteme.database.NotesDatabase
import com.example.noteme.databinding.ActivityMainBinding
import com.example.noteme.repo.NotesRepo
import com.example.noteme.viewmodel.NoteViewModel
import com.example.noteme.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this,navController)

        val repository = NotesRepo(NotesDatabase.getInstance(this))
        val viewModelFactory = NoteViewModelFactory(application,repository)
        viewModel = ViewModelProvider(this,viewModelFactory)[NoteViewModel::class.java]
    }
}