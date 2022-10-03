package notes_update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentUpdateBinding
import model.Notes
import main_fragment.NoteViewModel

class UpdateNoteFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private lateinit var viewModel: NoteViewModel
    private val args by navArgs<UpdateNoteFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = FragmentUpdateBinding.inflate(layoutInflater)

        // current data
        binding.updateTitle.setText(args.currentNote.title)
        binding.updateContent.setText(args.currentNote.content)

        // ViewModel
        viewModel = ViewModelProvider(requireActivity())[NoteViewModel::class.java]

        // Navigation
        binding.updateSave.setOnClickListener { updateData() }
        binding.updateBack.setOnClickListener { findNavController().navigate(R.id.action_updateNoteFragment_to_mainFragment) }
        binding.updateBottomSheetButton.setOnClickListener { findNavController().navigate(R.id.action_updateNoteFragment_to_bottomSheet) }


        return binding.root
    }

    private fun updateData() {
        val title = binding.updateTitle.text.toString()
        val content = "                 ${binding.updateContent.text}"
        //val image = binding.theUploadedImage

        // if it's not empty
        if(checkNote(title,content)){
            val note = Notes(args.currentNote.id,title,content)
            viewModel.updateNote(note)
            Toast.makeText(requireContext(),"done", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateNoteFragment_to_mainFragment)
        }else{
            Toast.makeText(requireContext(),"something wont wrong", Toast.LENGTH_SHORT).show()
        }
    }
    // useless fun but maybe ues it later
    private fun checkNote(title:String, content:String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))
    }
}