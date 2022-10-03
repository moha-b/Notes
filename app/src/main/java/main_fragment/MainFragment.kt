package main_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var viewModel: NoteViewModel
    private lateinit var binding: FragmentMainBinding
    private val adapter =  Adapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = FragmentMainBinding.inflate(layoutInflater)

        binding.bottomNavigationBar.background = null

        // ViewModel
        viewModel = ViewModelProvider(requireActivity())[NoteViewModel::class.java]
        viewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)
        })

        // Navigation
        binding.fab.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_addNotes) }

        // Recycler View
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)


        return binding.root
    }

}