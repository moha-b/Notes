package main_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = FragmentMainBinding.inflate(layoutInflater)

        binding.bottomNavigationBar.background = null

        // Navigation
        binding.fab.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_addNotes) }


        return binding.root
    }

}