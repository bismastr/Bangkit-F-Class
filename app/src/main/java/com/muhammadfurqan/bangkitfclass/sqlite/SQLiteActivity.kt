package com.muhammadfurqan.bangkitfclass.sqlite

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muhammadfurqan.bangkitfclass.R
import com.muhammadfurqan.bangkitfclass.databinding.ActivitySqliteBinding
import com.muhammadfurqan.bangkitfclass.sqlite.adapter.BookAdapter
import com.muhammadfurqan.bangkitfclass.sqlite.adapter.viewholder.BookViewHolder
import com.muhammadfurqan.bangkitfclass.sqlite.db.BookDatabaseManager
import kotlinx.coroutines.launch

/**
 *
 * Contact : 081375496583
 *
 * Step :
 * 1. Fork our Repository (https://github.com/fueerqan/Bangkit-F-Class)
 *
 * CHALLENGE :
 * 1. Recycler View to show all of the data, previously we only show them in toast
 * 2. Add Function to edit the books data for each item in your Recycler View Items
 * 3. Add Function to delete the books data for each item in your Recycler View Items
 * 4. Notify Data Changes for you Recycler View
 *
 * Reward : Rp20.000 Go-Pay / OVO
 * Limit : No Limit Person
 * Dateline : 23.00
 *
 * Submit to https://forms.gle/CytSQSyQDJeivpkd7
 *
 */

class SQLiteActivity : AppCompatActivity(), BookViewHolder.Listener {
    private lateinit var binding: ActivitySqliteBinding
    private lateinit var etBookName: AppCompatEditText
    private lateinit var btnAdd: AppCompatButton
    private lateinit var btnRead: AppCompatButton
    private lateinit var adapter: BookAdapter
    private val bookDb: BookDatabaseManager by lazy {
        BookDatabaseManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySqliteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etBookName = findViewById(R.id.et_book_name)
        btnAdd = findViewById(R.id.btn_add)
        btnRead = findViewById(R.id.btn_read)

        initRv()

        btnAdd.setOnClickListener {
            onAdd()
        }

        btnRead.setOnClickListener {
            onRead()
        }
    }

    private fun onAdd() {
        val bookName = etBookName.text.toString()
        if (bookName.isNotEmpty()) {
            lifecycleScope.launch {
                bookDb.saveData(bookName)
            }
            etBookName.setText("")
        } else {
            Toast.makeText(this, "Please fill in the book name", Toast.LENGTH_SHORT).show()
        }
        initRv()

    }

    override fun onResume() {
        super.onResume()
        initRv()
    }

    private fun initRv(){
        adapter = BookAdapter(this)
        adapter.notifyDataSetChanged()
        val bookList = ArrayList(bookDb.getData())
        adapter.setData(bookList)
        binding.rvBookList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvBookList.adapter = adapter
    }

    private fun onRead() {
        val bookList = ArrayList(bookDb.getData())


        val bookListString = bookList.joinToString(separator = "\n") {
            "Book ${it.id} is ${it.name}"
        }
        Toast.makeText(this, bookListString, Toast.LENGTH_SHORT).show()
    }
    //OnCLick
    override fun onClick(element: BookModel) {
        startActivity(
            Intent(this, DetailBookActivity::class.java)
                .putExtra(DetailBookActivity.EXTRA_NAME, element.name)
                .putExtra(DetailBookActivity.EXTRA_ID, element.id)
        )
    }

}