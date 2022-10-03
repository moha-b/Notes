package notes_create

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
import main_fragment.NoteViewModel
import model.Notes


class CreateNoteFragment : Fragment() {
    private lateinit var viewModel: NoteViewModel
    private lateinit var binding: FragmentCreateNoteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCreateNoteBinding.inflate(layoutInflater)

        // ViewModel
        viewModel = ViewModelProvider(requireActivity())[NoteViewModel::class.java]

        // Navigation
        binding.createSave.setOnClickListener {
            // Add new Note in Home Page
            newNote()
        }
        binding.createBack.setOnClickListener { findNavController().navigate(R.id.action_addNotes_to_mainFragment) }


        return binding.root
    }

    private fun newNote(){
        val title = binding.createTitle.text.toString()
        val content = binding.createContent.text.toString()
        //val image = binding.theUploadedImage
        // if it's not empty
        if(checkNote(title,content)){
            val note = Notes(0,title,content)
            viewModel.addNote(note)
            findNavController().navigate(R.id.action_addNotes_to_mainFragment)
            Toast.makeText(requireContext(),"successfully, created",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(),"something wont wrong",Toast.LENGTH_SHORT).show()
        }
    }

    // useless fun but maybe ues it later
    private fun checkNote(title:String, content:String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))
    }
}