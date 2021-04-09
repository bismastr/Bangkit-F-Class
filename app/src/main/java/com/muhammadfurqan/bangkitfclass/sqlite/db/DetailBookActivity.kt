package com.muhammadfurqan.bangkitfclass.sqlite.db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.muhammadfurqan.bangkitfclass.R
import com.muhammadfurqan.bangkitfclass.databinding.ActivityDetailBinding
import com.muhammadfurqan.bangkitfclass.databinding.ActivityDetailBookBinding
import com.muhammadfurqan.bangkitfclass.sqlite.BookModel
import com.muhammadfurqan.bangkitfclass.sqlite.adapter.BookAdapter
import com.muhammadfurqan.bangkitfclass.sqlite.adapter.viewholder.BookViewHolder
import kotlinx.coroutines.launch

class DetailBookActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var adapter: BookAdapter
    private val bookDb: BookDatabaseManager by lazy {
        BookDatabaseManager(this)
    }
    private lateinit var binding: ActivityDetailBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //get intent data
        val name = intent.getStringExtra(EXTRA_NAME)
        val id = intent.getIntExtra(EXTRA_ID, 0)

        //edtText
        binding.edtDetail.setText(name)
        //setOnClickListener
        binding.btnDelete.setOnClickListener {
         onDelete(id.toString())
        }
        binding.btnUpdate.setOnClickListener {
            onUpdate(id.toString())
        }
    }

    private fun onUpdate(id: String) {
        Log.d("ID", id)
        val bookName = binding.edtDetail.text.toString()
        if (bookName.isNotEmpty()) {
            lifecycleScope.launch {
                bookDb.updateData(bookName, id)
            }
            binding.edtDetail.setText(bookName)
            finish()
        } else {
            Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onDelete(id: String) {
        Log.d("ID", id)
        lifecycleScope.launch {
            bookDb.delete(id)
        }
        finish()
    }
}

