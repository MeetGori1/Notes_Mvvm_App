package com.example.notesmvvmapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesmvvmapp.databinding.ActivityAddNoteBinding
import com.example.notesmvvmapp.models.Note
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date

class AddNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddNoteBinding
    lateinit var oldNote: Note
    lateinit var note: Note
    var isUpdate = false
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        try {
            oldNote = intent.getSerializableExtra("current_note") as Note
            binding.txtNote.setText(oldNote.note)
            binding.editTitle.setText(oldNote.title)
            isUpdate = true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        binding.imgCheck.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val note_desc = binding.txtNote.text.toString()

            if (title.isNotEmpty() || note_desc.isNotEmpty()) {
                val formatter = SimpleDateFormat("EEE,d MMM yyyy HH:mm a")

                if (isUpdate) {
                    note = Note(oldNote.id, title, note_desc, formatter.format(Date()))
                } else {
                    note = Note(null, title, note_desc, formatter.format(Date()))
                }


                val intent = Intent()
                intent.putExtra("note", note)
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Please Enter something.....", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }
}