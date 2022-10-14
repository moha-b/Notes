package notes_update

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentUpdateBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import model.Notes
import main_fragment.NoteViewModel

class UpdateNoteFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private lateinit var viewModel: NoteViewModel
    private val args by navArgs<UpdateNoteFragmentArgs>()
    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!
                args.currentNote.image = result.data?.data!!.toString()
                binding.updateTheUploadedImage.setImageURI(fileUri)
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
            updateDelete.setOnClickListener { deleteAlert() }
            updateUpload.setOnClickListener { pickImageFromGallery() }
        }
        changeNoteColor()

        return binding.root
    }

    private fun pickImageFromGallery() {
        ImagePicker.with(this)
            .compress(1024)         //Final image size will be less than 1 MB(Optional)
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
            }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun changeNoteColor() {
        binding.apply {
            updateBlue.setOnClickListener {
                args.currentNote.color = R.drawable.color1
                Toast.makeText(requireActivity(),"Color has been Changed",Toast.LENGTH_SHORT).show()
            }
            updatePink.setOnClickListener {
                args.currentNote.color = R.drawable.color4
                Toast.makeText(requireActivity(),"Color has been Changed",Toast.LENGTH_SHORT).show()
            }
            updatePurple.setOnClickListener {
                args.currentNote.color = R.drawable.color2
                Toast.makeText(requireActivity(),"Color has been Changed",Toast.LENGTH_SHORT).show()
            }
            updateLemon.setOnClickListener {
                args.currentNote.color = R.drawable.color3
                Toast.makeText(requireActivity(),"Color has been Changed",Toast.LENGTH_SHORT).show()
            }
            updateYellow.setOnClickListener {
                args.currentNote.color = R.drawable.color5
                Toast.makeText(requireActivity(),"Color has been Changed",Toast.LENGTH_SHORT).show()
            }
        }
    }

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