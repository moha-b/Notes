package notes_create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import main_fragment.NoteViewModel

class BottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding
    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = FragmentBottomSheetBinding.inflate(layoutInflater)

        // ViewModel
        viewModel = ViewModelProvider(requireActivity())[NoteViewModel::class.java]

        binding.uploadImage.setOnClickListener {
           // upload images
        }

        binding.delete.setOnClickListener {
            // delete note

        }

        return binding.root
    }

}