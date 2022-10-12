package notes_update

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
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
import androidx.core.content.ContextCompat
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
//    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_open) }
//    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_close) }
//    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.from_bottom) }
//    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.to_bottom) }
////    private val fromLeft: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.fab_from_left) }
////    private val toLeft: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.fab_to_left) }
////    private val fromAngel: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.fab_from_angel) }
////    private val toAngel: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.fab_to_angel) }
//    private var clicked = false
    private val pickImage = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            //  you will get result here in result.data
            val uri = result.data?.data!!
            requireActivity().contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            // Do something else with the URI. E.g, save the URI as a string in the database
            args.currentNote.image = result.data?.data!!.toString()
            binding.updateTheUploadedImage.setImageURI(uri)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = FragmentUpdateBinding.inflate(layoutInflater)

        // current data
        binding.updateTitle.setText(args.currentNote.title)
        binding.updateContent.setText(args.currentNote.content)
        val uri = Uri.parse(args.currentNote.image)
        binding.updateTheUploadedImage.setImageURI(uri)

        // ViewModel
        viewModel = ViewModelProvider(requireActivity())[NoteViewModel::class.java]

        // Navigation
        binding.apply {
            updateBack.setOnClickListener { updateData() }

//            updateEdit.setOnClickListener {
//                clicked = !clicked
//                setVisibility(clicked)
//                setAnimation(clicked)
//                setClickable(clicked)
//            }

            updateDelete.setOnClickListener {
                deleteAlert()
            }

            updateUpload.setOnClickListener {
                pickImageFromGallery()
            }
        }

        return binding.root
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        pickImage.launch(Intent.createChooser(intent,"Pick Image"))
    }

//    @SuppressLint("UseCompatLoadingForDrawables")
//    private fun changeNoteColor() {
//        binding.apply {
//            updateBlue.setOnClickListener {
//                args.currentNote.color = R.drawable.color1
//                Toast.makeText(requireActivity(),"Color has been Changed",Toast.LENGTH_SHORT).show()
//            }
//            updatePink.setOnClickListener {
//                args.currentNote.color = R.drawable.color4
//                Toast.makeText(requireActivity(),"Color has been Changed",Toast.LENGTH_SHORT).show()
//            }
//            updatePurple.setOnClickListener {
//                args.currentNote.color = R.drawable.color2
//                Toast.makeText(requireActivity(),"Color has been Changed",Toast.LENGTH_SHORT).show()
//            }
//            updateLemon.setOnClickListener {
//                args.currentNote.color = R.drawable.color3
//                Toast.makeText(requireActivity(),"Color has been Changed",Toast.LENGTH_SHORT).show()
//            }
//            updateYellow.setOnClickListener {
//                args.currentNote.color = R.drawable.color5
//                Toast.makeText(requireActivity(),"Color has been Changed",Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    private fun updateData() {
        val title = binding.updateTitle.text.toString()
        val content = binding.updateContent.text.toString()
        //val image = binding.theUploadedImage

        // if it's not empty
        if(checkNote(title,content)){
            val note = Notes(args.currentNote.id,title,content,args.currentNote.color,args.currentNote.image)
            viewModel.updateNote(note)
            //Toast.makeText(requireContext(),"successfully, updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateNoteFragment_to_mainFragment)
        }else{
            Toast.makeText(requireContext(),"something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
    // useless fun but maybe ues it later
    private fun checkNote(title:String, content:String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))
    }
//
//    private fun setVisibility(clicked: Boolean) {
//        if (clicked){
//            binding.updateDelete.visibility = View.VISIBLE
//            binding.updateUpload.visibility = View.VISIBLE
//        }else{
//            binding.updateDelete.visibility = View.INVISIBLE
//            binding.updateUpload.visibility = View.INVISIBLE
//        }
//    }
//
//    private fun setClickable(clicked: Boolean) {
//        if (clicked){
//            binding.updateDelete.isClickable = true
//            binding.updateUpload.isClickable = true
//        }else{
//            binding.updateDelete.isClickable = false
//            binding.updateUpload.isClickable = false
//        }
//    }
//
//    private fun setAnimation(clicked: Boolean) {
//        if (clicked){
//            binding.updateEdit.startAnimation(rotateOpen)
//            binding.updateDelete.startAnimation(fromBottom)
//            binding.updateUpload.startAnimation(fromBottom)
//        }else{
//            binding.updateEdit.startAnimation(rotateClose)
//            binding.updateDelete.startAnimation(toBottom)
//            binding.updateUpload.startAnimation(toBottom)
//        }
//    }

    private fun deleteAlert() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){_,_ ->
            viewModel.deleteNote(args.currentNote)
            findNavController().navigate(R.id.action_updateNoteFragment_to_mainFragment)
        }
        builder.setNegativeButton("no"){_,_ -> }
        builder.setTitle("Delete ${args.currentNote.title} ?")
        builder.setIcon(R.drawable.ic_delete)
        builder.setMessage("Are you sure you want to delete ${args.currentNote.title} ?")
        builder.create().show()
    }

}