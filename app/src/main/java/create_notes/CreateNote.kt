package create_notes

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentCreateNoteBinding
import model.Notes


class CreateNote : Fragment() {
    private lateinit var viewModel: NoteViewModel
    private lateinit var binding: FragmentCreateNoteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCreateNoteBinding.inflate(layoutInflater)

        // ViewModel
        viewModel = ViewModelProvider(requireActivity())[NoteViewModel::class.java]


        // Navigation
        binding.save.setOnClickListener {
            newNote()
            findNavController().navigate(R.id.action_addNotes_to_mainFragment)
        }
        binding.back.setOnClickListener { findNavController().navigate(R.id.action_addNotes_to_mainFragment) }
        binding.bottomSheetButton.setOnClickListener { findNavController().navigate(R.id.action_addNotes_to_bottomSheet) }



        return binding.root
    }

    private fun newNote(){
        val title = binding.title.text.toString()
        val content = binding.content.text.toString()
        //val image = binding.theUploadedImage
        // if it's not empty
        if(checkNote(title,content)){
            val note = Notes(0,title,content)
            viewModel.addNote(note)
            Toast.makeText(requireContext(),"done",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(),"something wont wrong",Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkNote(title:String, content:String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))
    }
}