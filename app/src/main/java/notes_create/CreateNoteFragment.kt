package notes_create

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentCreateNoteBinding
import main_fragment.NoteViewModel
import model.Notes


class CreateNoteFragment : Fragment() {

    private lateinit var viewModel: NoteViewModel
    private lateinit var binding: FragmentCreateNoteBinding
    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_open) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_close) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.from_bottom) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.to_bottom) }
    private val fromLeft: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.fab_from_left) }
    private val toLeft: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.fab_to_left) }
    private var clicked = false
    private var color = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCreateNoteBinding.inflate(layoutInflater)

        // ViewModel
        viewModel = ViewModelProvider(requireActivity())[NoteViewModel::class.java]

        // Navigation
        binding.createEdit.setOnClickListener {
            clicked = !clicked
            setVisibility(clicked)
            setAnimation(clicked)
            setClickable(clicked)
        }
        binding.createBack.setOnClickListener { // Add new Note in Home Page
            newNote()
        }

        changeNoteColor()

        return binding.root
    }

    private fun newNote(){
        val title = binding.createTitle.text.toString()
        val content = binding.createContent.text.toString()

        //val image = binding.theUploadedImage
        // if it's not empty
        if(checkNote(title,content)){
            val note = Notes(0,title,content,color)
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

    private fun setVisibility(clicked: Boolean) {
        if (clicked){
            binding.createUpload.visibility = View.VISIBLE
            binding.createColorMe.visibility = View.VISIBLE
        }else{
            binding.createUpload.visibility = View.INVISIBLE
            binding.createColorMe.visibility = View.INVISIBLE
        }
    }

    private fun setClickable(clicked: Boolean) {
        if (clicked){
            binding.createUpload.isClickable = true
            binding.createColorMe.isClickable = true
        }else{
            binding.createUpload.isClickable = false
            binding.createColorMe.isClickable = false
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (clicked){
            binding.createEdit.startAnimation(rotateOpen)
            binding.createUpload.startAnimation(fromBottom)
            binding.createColorMe.startAnimation(fromLeft)
        }else{
            binding.createEdit.startAnimation(rotateClose)
            binding.createUpload.startAnimation(toBottom)
            binding.createColorMe.startAnimation(toLeft)
        }
    }
}