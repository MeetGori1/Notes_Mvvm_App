package com.example.notesmvvmapp.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesmvvmapp.R
import com.example.notesmvvmapp.databinding.ListItemBinding
import com.example.notesmvvmapp.models.Note
import kotlin.random.Random

class NotesAdapter(private val context: Context, val listener: NotesClickListener) :
    RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {
     var notesList= ArrayList<Note>()
     var fullList= ArrayList<Note>()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): MyViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentNote = notesList[position]
        holder.binding.txtTitle.text = currentNote.title
        holder.binding.txtTitle.isSelected = true
        holder.binding.txtNote.text = currentNote.note
        holder.binding.txtDate.text = currentNote.date
        holder.binding.txtDate.isSelected = true
        holder.binding.cardView.setCardBackgroundColor(
            holder.itemView.resources.getColor(
                randomColor(),
                null
            )
        )
        holder.bindItem(notesList[position])

        holder.binding.cardView.setOnClickListener {
            listener.onItemClicked(notesList[holder.adapterPosition])
        }

        holder.binding.cardView.setOnLongClickListener {
            listener.onLongItemClicked(notesList[holder.adapterPosition], holder.binding.cardView)
            true
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun randomColor(): Int {
        val list = ArrayList<Int>()
        list.add(R.color.FireBrick)
        list.add(R.color.GreenYellow)
        list.add(R.color.DarkSeaGreen)
        list.add(R.color.MediumVioletRed)
        list.add(R.color.RosyBrown)
        list.add(R.color.Sienna)
        list.add(R.color.LightBlue)
        list.add(R.color.MediumPurple)

        val seed = System.currentTimeMillis().toInt()
        val randomINdex = Random(seed).nextInt(list.size)
        return list[randomINdex]
    }

    inner class MyViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(bean: Note) {
            binding.txtNote.text = bean.note
            binding.txtTitle.text = bean.title
            binding.txtDate.text = bean.date

        }
    }

    fun updateList(newList: List<Note>) {
        fullList.clear()
        fullList.addAll(newList)

        notesList.clear()
        notesList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filterList(search:String){
        notesList.clear()

        for (item in fullList)
        {
            if (item.title?.lowercase()?.contains(search.lowercase() )==true|| item.note?.lowercase()
                    ?.contains(search.lowercase())==true){
                notesList.add(item)
            }
        }

        notifyDataSetChanged()
    }
    interface NotesClickListener {
        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note, cardView: CardView)
    }
}