package main_fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.FragmentMainBinding
import com.example.noteapp.databinding.NoteLayoutBinding
import model.Notes

class Adapter: RecyclerView.Adapter<Adapter.MyViewHolder>() {

    var list = emptyList<Notes>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(notes: List<Notes>){
        list = notes
        notifyDataSetChanged()
    }

    class MyViewHolder(val binding: NoteLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(NoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(Holder: MyViewHolder, position: Int) {
        val item = list[position]
        Holder.binding.apply {
            noteTitle.text = item.title
            noteContent.text = item.content

            note.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToUpdateNoteFragment(item)
                Holder.binding.note.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int = list.size
}