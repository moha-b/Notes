package create_notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentCreateNoteBinding


class CreateNote : Fragment() {
    private lateinit var binding: FragmentCreateNoteBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCreateNoteBinding.inflate(layoutInflater)

        // Navigation
        binding.save.setOnClickListener { findNavController().navigate(R.id.action_addNotes_to_mainFragment) }
        binding.back.setOnClickListener { findNavController().navigate(R.id.action_addNotes_to_mainFragment) }
        binding.bottomSheetButton.setOnClickListener { findNavController().navigate(R.id.action_addNotes_to_bottomSheet) }


        return binding.root
    }
}