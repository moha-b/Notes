package notes_create

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentCreateNoteBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import main_fragment.NoteViewModel
import model.Notes

class CreateNoteFragment : Fragment() {

    var image: String = ""
    private lateinit var viewModel: NoteViewModel
    private lateinit var binding: FragmentCreateNoteBinding
    private var color = 0
    var like = 0
    private val startForProfileImageResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data
            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!
                image = result.data?.data!!.toString()

                binding.createTheUploadedImage.setImageURI(fileUri)
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCreateNoteBinding.inflate(layoutInflater)

        // ViewModel
        viewModel = ViewModelProvider(requireActivity())[NoteViewModel::class.java]

        // Navigation
        binding.createBack.setOnClickListener { // Add new Note in Home Page
            newNote()
        }
        binding.createUpload.setOnClickListener {
            pickImageFromGallery()
        }
        binding.createTheUploadedImage.setOnClickListener {
            like = 1
            Toast.makeText(requireContext(),"add to fav",Toast.LENGTH_SHORT).show()
        }
        binding.maps.setOnClickListener {
            findNavController().navigate(R.id.action_addNotes_to_mapsFragment)
        }

        changeNoteColor()

        return binding.root
    }

    private fun pickImageFromGallery() {
        ImagePicker.with(this)
            .compress(1100) //Final image size will be less than 1 MB(Optional)
            .crop()
            .maxResultSize(1080, 1080)
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
            }
    }

    private fun newNote(){
        val title = binding.createTitle.text.toString()
        val content = binding.createContent.text.toString()

        //val image = binding.theUploadedImage
        // if it's not empty
        if(checkNote(title,content)){
            val note = Notes(0,title,content,color,image,like)
            viewModel.addNote(note)
            findNavController().navigate(R.id.action_addNotes_to_mainFragment)
            Toast.makeText(requireContext(),"successfully, created",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(),"something wont wrong",Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkNote(title:String, content:String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))
    }

    private fun changeNoteColor() {
        binding.apply {
            createBlue.setOnClickListener {
                color = R.drawable.color1
                Toast.makeText(requireActivity(),"Color has been Changed",Toast.LENGTH_SHORT).show()
            }
            createPink.setOnClickListener {
                color = R.drawable.color4
                Toast.makeText(requireActivity(),"Color has been Changed",Toast.LENGTH_SHORT).show()
            }
            createPurple.setOnClickListener {
                color = R.drawable.color2
                Toast.makeText(requireActivity(),"Color has been Changed",Toast.LENGTH_SHORT).show()
            }
            createLemon.setOnClickListener {
                color = R.drawable.color3
                Toast.makeText(requireActivity(),"Color has been Changed",Toast.LENGTH_SHORT).show()
            }
            createYellow.setOnClickListener {
                color = R.drawable.color5
                Toast.makeText(requireActivity(),"Color has been Changed",Toast.LENGTH_SHORT).show()

            }
        }
    }

}