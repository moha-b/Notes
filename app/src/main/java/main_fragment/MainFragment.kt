package main_fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
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

         /*
        //to Support action bar in Fragment
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        */

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

        binding.imageProfile.setOnClickListener {
            viewModel.favNotes.observe(viewLifecycleOwner, Observer { fav ->
                adapter.setData(fav)
            })
        }

        // Search bar
        binding.mainSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null){
                    search(query)
                }
                return true
            }
            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null){
                    search(query)
                }
                return true
            }
        })
        return binding.root
    }

    fun search(query: String){
        val searchQuery = "%$query%"
        viewModel.search(searchQuery).observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)
        })
    }
}